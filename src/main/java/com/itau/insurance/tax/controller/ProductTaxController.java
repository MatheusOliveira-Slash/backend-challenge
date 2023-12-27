package com.itau.insurance.tax.controller;

import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.entity.id.ProductTaxId;
import com.itau.insurance.tax.exception.BadRequestException;
import com.itau.insurance.tax.exception.NotFoundException;
import com.itau.insurance.tax.model.ProductTaxModel;
import com.itau.insurance.tax.model.ProductTaxResponseModel;
import com.itau.insurance.tax.model.base.BaseModel;
import com.itau.insurance.tax.service.ProductTaxService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("product")
public class ProductTaxController {

    private ProductTaxService service;
    ProductTaxController(ProductTaxService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BaseModel>> getAll() {
        log.info("Fetching all products");

        List<ProductTaxEntity> entities = service.findAll();
        List<BaseModel> response = entities.stream()
                .map(entity -> new ProductTaxResponseModel().fromEntity(entity))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<BaseModel> get(@PathVariable("id") String requestId) {
        log.info("Fetching product with id: {}", requestId);

        ProductTaxId id = new ProductTaxId(requestId);

        Optional<ProductTaxEntity> entityResponse = service.findById(id);

        if(!entityResponse.isPresent()) {
            throw new NotFoundException("Record not found",  id.toString());
        }

        return ResponseEntity.ok(
                new ProductTaxResponseModel().fromEntity(entityResponse.get()));

    }

    @PostMapping
    public ResponseEntity<BaseModel> post(@Valid @RequestBody ProductTaxModel requestBody){

        log.info("Posting new product");

        ProductTaxEntity entityResponse = service.post(requestBody.toEntity());

        log.info("Product posted: " + entityResponse.getId().getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ProductTaxResponseModel().fromEntity(entityResponse));

    }

    @PutMapping("{id}")
    public ResponseEntity<BaseModel> put(@PathVariable("id") String id, @Valid @RequestBody ProductTaxModel requestBody) {
        log.info("Updating product with id: {}", id);

        ProductTaxEntity entityResponse = service.insertOrUpdate(new ProductTaxId(id), requestBody.toEntity());

        log.info("Product updated: {}", id);

        return ResponseEntity.ok(
                new ProductTaxResponseModel().fromEntity(entityResponse));
    }

    @PatchMapping("{id}")
    public ResponseEntity<BaseModel> patch(@PathVariable("id") String id,
                                           @RequestBody Map<String, Object> updates) {
        log.info("Partially updating product with id: {}", id);

        ProductTaxModel productTaxModel = getModelFromMap(updates);

        ProductTaxEntity entityResponse = service.update(new ProductTaxId(id), productTaxModel.toEntity());
        ProductTaxResponseModel responseModel =
                (ProductTaxResponseModel) new ProductTaxResponseModel().fromEntity(entityResponse);

        log.info("Product updated: {}", id);

        return ResponseEntity.ok(responseModel);
    }

    @SneakyThrows
    private ProductTaxModel getModelFromMap(Map<String, Object> updates) {

        try {

            ProductTaxModel productTaxModel = new ProductTaxModel();

            if (updates.containsKey("nome") && !String.valueOf(updates.get("nome")).isBlank())
                productTaxModel.setNome((String) updates.get("nome"));

            if (updates.containsKey("preco_base") && (Double) updates.get("preco_base") > 0)
                productTaxModel.setPrecoBase(BigDecimal.valueOf((Double) updates.get("preco_base")));

            if (updates.containsKey("categoria"))
                productTaxModel.setCategoria((String) updates.get("categoria"));

            return productTaxModel;

        } catch (Exception e){
            throw new BadRequestException("Erro ao gerar objeto para atualização", "");
        }

    }


}
