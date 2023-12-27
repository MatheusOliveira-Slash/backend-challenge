package com.itau.insurance.tax.controller;

import com.itau.insurance.tax.domain.AssuranceCategoryTaxDomain;
import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.entity.id.ProductTaxId;
import com.itau.insurance.tax.exception.NotFoundException;
import com.itau.insurance.tax.model.ProductTaxModel;
import com.itau.insurance.tax.model.ProductTaxResponseModel;
import com.itau.insurance.tax.model.base.BaseModel;
import com.itau.insurance.tax.service.ProductTaxService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductTaxControllerTest {

    @InjectMocks
    private ProductTaxController controller;

    @Mock
    private ProductTaxService service;

    @Test
    public void getAllShouldReturnListOfProducts() {
        List<ProductTaxEntity> entities = Arrays.asList(getProductTaxEntity(), getProductTaxEntity());
        when(service.findAll()).thenReturn(entities);

        ResponseEntity<List<BaseModel>> response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().get(0) instanceof ProductTaxResponseModel);
    }

    @Test
    public void getShouldReturnProductById() {
        ProductTaxId id = getProductTaxId();
        when(service.findById(id)).thenReturn(Optional.of(getProductTaxEntity()));

        ResponseEntity<BaseModel> response = controller.get(id.getId().toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof ProductTaxResponseModel);
    }

    @Test
    public void getShouldThrowNotFoundExceptionWhenProductNotFound() {
        ProductTaxId id = getProductTaxId();
        when(service.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> controller.get(id.getId().toString()));
    }

    @Test
    public void postShouldCreateNewProduct() {
        ProductTaxModel requestBody = getProductTaxModel();
        when(service.post(any(ProductTaxEntity.class))).thenReturn(getProductTaxEntity());

        ResponseEntity<BaseModel> response = controller.post(requestBody);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof ProductTaxResponseModel);
    }

    private ProductTaxId getProductTaxId() {
        return new ProductTaxId(UUID.randomUUID().toString());
    }

    private ProductTaxEntity getProductTaxEntity() {
        return new ProductTaxEntity(getProductTaxId(), "Test Product", AssuranceCategoryTaxDomain.LIFE,
                BigDecimal.valueOf(100.0), null);
    }

    private ProductTaxModel getProductTaxModel() {
        return (ProductTaxModel) new ProductTaxModel().fromEntity(getProductTaxEntity());
    }
}
