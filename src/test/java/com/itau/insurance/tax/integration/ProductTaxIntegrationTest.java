package com.itau.insurance.tax.integration;

import com.itau.insurance.tax.entity.id.ProductTaxId;
import com.itau.insurance.tax.model.ProductTaxResponseModel;
import com.itau.insurance.tax.repository.ProductTaxRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTaxIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ProductTaxRepository repository;

    private String strRequest = "{" +
            "\"nome\": \"Seguro de Vida Individual\"," +
            "\"categoria\": \"VIDA\"," +
            "\"preco_base\": 100.0" +
            "}";

    HttpHeaders headers = new HttpHeaders();

    HttpEntity<String> baseRequest;

    @BeforeEach
    public void setup(){
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        baseRequest = new HttpEntity<>(strRequest, headers);
    }

    @SneakyThrows
    @Test
    public void postShouldReturn201CreatedWithValidId() {

        ResponseEntity<ProductTaxResponseModel> response = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertTrue(isValidUUID(response.getBody()));

        repository.deleteById(new ProductTaxId(response.getBody().getId()));
    }

    @Test
    public void getShouldReturn200WithSameCreatedId() {

        ResponseEntity<ProductTaxResponseModel> postResponse = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());

        ResponseEntity<ProductTaxResponseModel> getResponse = restTemplate.getForEntity("/product/{id}", ProductTaxResponseModel.class, postResponse.getBody().getId());

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(getResponse.getBody().getId(), postResponse.getBody().getId());

        repository.deleteById(new ProductTaxId(postResponse.getBody().getId()));
    }

    @Test
    public void getAllShouldReturn200WithListOfItens() {

        HashSet<String> createdIds = new HashSet<>();

        ResponseEntity<ProductTaxResponseModel> postResponse = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        createdIds.add(postResponse.getBody().getId());

        postResponse = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        createdIds.add(postResponse.getBody().getId());

        postResponse = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        createdIds.add(postResponse.getBody().getId());

        ParameterizedTypeReference<List<ProductTaxResponseModel>> listResponseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<ProductTaxResponseModel>> getResponse = restTemplate.exchange("/product", HttpMethod.GET, null, listResponseType);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertTrue(getResponse.getBody().size() > 1);

        AtomicReference<Integer> aux = new AtomicReference<>(0);

        getResponse.getBody().forEach(item -> {
            if(createdIds.contains(item.getId())){
                 aux.getAndSet(aux.get() + 1);
                 repository.deleteById(new ProductTaxId(item.getId()));
            }
        });

        assertEquals(aux.get(), createdIds.size());
    }

    @Test
    public void putShouldReturnUpdatedRecord() {

        ResponseEntity<ProductTaxResponseModel> postResponse = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());

        baseRequest = new HttpEntity<>(strRequest.replace("100.0", "500.0"), headers);
        restTemplate.put("/product/{id}", baseRequest, postResponse.getBody().getId());

        ResponseEntity<ProductTaxResponseModel> getResponse = restTemplate.getForEntity("/product/{id}", ProductTaxResponseModel.class, postResponse.getBody().getId());

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(getResponse.getBody().getId(), postResponse.getBody().getId());
        assertNotEquals(getResponse.getBody().getPrecoBase(), postResponse.getBody().getPrecoBase());
        assertEquals(getResponse.getBody().getPrecoBase(), BigDecimal.valueOf(500.0).setScale(2, RoundingMode.UP));

        repository.deleteById(new ProductTaxId(postResponse.getBody().getId()));
    }

    @Test
    public void putShouldReturnNewRecord() {

        ParameterizedTypeReference<List<ProductTaxResponseModel>> listResponseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<ProductTaxResponseModel>> getResponse = restTemplate.exchange("/product", HttpMethod.GET, null, listResponseType);
        assertTrue(getResponse.getBody().size() == 0);

        restTemplate.put("/product/{id}", baseRequest, UUID.randomUUID());

        getResponse = restTemplate.exchange("/product", HttpMethod.GET, null, listResponseType);
        assertTrue(getResponse.getBody().size() == 1);

        repository.deleteById(new ProductTaxId(getResponse.getBody().get(0).getId()));
    }

//    @Test
    public void patchShouldReturn200WithUpdatedRecord() {

        ResponseEntity<ProductTaxResponseModel> postResponse = restTemplate.postForEntity("/product", baseRequest, ProductTaxResponseModel.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());

        baseRequest = new HttpEntity<>(strRequest.replace("100.0", "500.0"), headers);
        restTemplate.patchForObject("/product/{id}", baseRequest, ProductTaxResponseModel.class, postResponse.getBody().getId()); // TODO Trocar implementação, talvez com WebFlux

        ResponseEntity<ProductTaxResponseModel> getResponse = restTemplate.getForEntity("/product/{id}", ProductTaxResponseModel.class, postResponse.getBody().getId());

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(getResponse.getBody().getId(), postResponse.getBody().getId());
        assertNotEquals(getResponse.getBody().getPrecoBase(), postResponse.getBody().getPrecoBase());
        assertEquals(getResponse.getBody().getPrecoBase(), BigDecimal.valueOf(500.0));

        repository.deleteById(new ProductTaxId(postResponse.getBody().getId()));
    }

    private boolean isValidUUID(ProductTaxResponseModel responseModel) {

        try {

            UUID.fromString(responseModel.getId());
            return true;

        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
