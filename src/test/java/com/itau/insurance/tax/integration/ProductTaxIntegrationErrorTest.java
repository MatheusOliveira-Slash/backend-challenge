package com.itau.insurance.tax.integration;

import com.itau.insurance.tax.model.ProductTaxResponseModel;
import com.itau.insurance.tax.repository.ProductTaxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTaxIntegrationErrorTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ProductTaxRepository repository;

    private String strRequest = "{" +
            "\"nome\": \"Seguro de Vida Individual\"," +
            "\"categoria\": \"VIDA\"," +
            "\"preco_base\": 100.00" +
            "}";

    HttpHeaders headers = new HttpHeaders();

    HttpEntity<String> baseRequest;

    @BeforeEach
    public void setup(){
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        baseRequest = new HttpEntity<>(strRequest, headers);
    }

    @Test
    public void postShouldReturn400ToInvalidDataType(){

        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN.toString());
        baseRequest = new HttpEntity<>(strRequest, headers);

        ResponseEntity<ProductTaxResponseModel> response = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void postShouldReturn400ToMissingPrecoBase(){

        baseRequest = new HttpEntity<>(strRequest.replace("base", "basee"), headers);

        ResponseEntity<ProductTaxResponseModel> response = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void postShouldReturn400ToPrecoBaseLessThanZero(){

        baseRequest = new HttpEntity<>(strRequest.replace("100", "-100"), headers);

        ResponseEntity<ProductTaxResponseModel> response = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void postShouldReturn400ToMissingNome(){

        baseRequest = new HttpEntity<>(strRequest.replace("nome", "nomee"), headers);

        ResponseEntity<ProductTaxResponseModel> response = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void postShouldReturn400ToMissingCategoria(){

        baseRequest = new HttpEntity<>(strRequest.replace("categoria", "cat"), headers);

        ResponseEntity<ProductTaxResponseModel> response = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void postShouldReturn400ToInvalidCategoria(){

        baseRequest = new HttpEntity<>(strRequest.replace("VIDA", "V"), headers);

        ResponseEntity<ProductTaxResponseModel> response = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getShouldReturn400ToInvalidId(){

        ResponseEntity<ProductTaxResponseModel> getResponse = restTemplate.getForEntity("/product/{id}", ProductTaxResponseModel.class, "INVALID#$%¨&*()ID");

        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
    }

    @Test
    public void getShouldReturn404ToRecordNotFound(){

        ResponseEntity<ProductTaxResponseModel> getResponse = restTemplate.getForEntity("/product/{id}", ProductTaxResponseModel.class, UUID.randomUUID());

        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }



}
