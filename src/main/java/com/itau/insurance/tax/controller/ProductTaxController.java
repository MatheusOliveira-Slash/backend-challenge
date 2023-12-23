package com.itau.insurance.tax.controller;

import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.model.ProductTaxModel;
import com.itau.insurance.tax.model.ProductTaxResponseModel;
import com.itau.insurance.tax.service.ProductTaxService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("product")
public class ProductTaxController {

    private ProductTaxService service;
    ProductTaxController(ProductTaxService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductTaxResponseModel> post(@Valid @RequestBody ProductTaxModel requestBody){

        log.info("Posting new product");

        ProductTaxEntity entityResponse = service.post(requestBody.toEntity());
        ProductTaxResponseModel responseModel =
                (ProductTaxResponseModel) new ProductTaxResponseModel().fromEntity(entityResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);

    }



}
