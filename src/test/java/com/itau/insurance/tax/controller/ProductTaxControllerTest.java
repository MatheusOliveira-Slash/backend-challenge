package com.itau.insurance.tax.controller;

import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.entity.id.ProductTaxId;
import com.itau.insurance.tax.exception.BadRequestException;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.itau.insurance.tax.Asserts.assertProductTaxResponseModel;
import static com.itau.insurance.tax.Mocks.getProductTaxEntity;
import static com.itau.insurance.tax.Mocks.getProductTaxId;
import static com.itau.insurance.tax.Mocks.getProductTaxModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
        assertProductTaxResponseModel((ProductTaxResponseModel) response.getBody().get(0));
    }

    @Test
    public void getShouldReturnProductById() {
        ProductTaxId id = getProductTaxId();
        when(service.findById(any())).thenReturn(Optional.of(getProductTaxEntity()));

        ResponseEntity<BaseModel> response = controller.get(id.getId().toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof ProductTaxResponseModel);
        assertProductTaxResponseModel((ProductTaxResponseModel) response.getBody());
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
        when(service.post(any())).thenReturn(getProductTaxEntity());

        ResponseEntity<BaseModel> response = controller.post(requestBody);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof ProductTaxResponseModel);
        assertProductTaxResponseModel((ProductTaxResponseModel) response.getBody());
    }

    @Test
    public void putShouldUpdateProduct() {
        ProductTaxModel requestBody = getProductTaxModel();
        when(service.insertOrUpdate(any(), any())).thenReturn(getProductTaxEntity());

        ResponseEntity<BaseModel> response = controller.put(getProductTaxId().getId().toString(), requestBody);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(service).insertOrUpdate(any(), any());
        assertTrue(response.getBody() instanceof ProductTaxResponseModel);
        assertProductTaxResponseModel((ProductTaxResponseModel) response.getBody());
    }

    @Test
    public void patchShouldPartiallyUpdateProduct() {

        Map<String, Object> updates = new HashMap<>();
        updates.put("nome", "New Name");

        when(service.update(any(), any())).thenReturn(getProductTaxEntity());

        ResponseEntity<BaseModel> response = controller.patch(getProductTaxId().getId().toString(), updates);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(service).update(any(), any());
        assertTrue(response.getBody() instanceof ProductTaxResponseModel);
        assertProductTaxResponseModel((ProductTaxResponseModel) response.getBody());

    }

    @Test
    public void patchShouldThrowBadRequestExceptionOnInvalidUpdates() {
        Map<String, Object> invalidUpdates = new HashMap<>();
        invalidUpdates.put("nome", "New Name");
        invalidUpdates.put("preco_base", "Invalid Price");

        assertThrows(BadRequestException.class, () -> controller.patch(getProductTaxId().getId().toString(), invalidUpdates));
    }

}
