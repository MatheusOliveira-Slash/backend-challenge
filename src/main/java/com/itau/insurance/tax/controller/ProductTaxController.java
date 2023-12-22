package com.itau.insurance.tax.controller;

import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.model.ProductTaxModel;
import com.itau.insurance.tax.service.ProductTaxService;
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
    public ResponseEntity<ProductTaxModel> post(@RequestBody ProductTaxModel requestBody){

        log.info("Posting new product");

        requestBody.isValid();

        ProductTaxEntity response = service.post(requestBody.toEntity());
        requestBody = (ProductTaxModel) requestBody.fromEntity(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(requestBody);

    }



}
