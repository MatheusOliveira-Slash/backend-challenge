package com.itau.insurance.tax.service;

import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.entity.id.ProductTaxId;
import com.itau.insurance.tax.exception.NotFoundException;
import com.itau.insurance.tax.repository.ProductTaxRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.itau.insurance.tax.Asserts.assertProductTaxEntity;
import static com.itau.insurance.tax.Mocks.getProductTaxEntity;
import static com.itau.insurance.tax.Mocks.getProductTaxId;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductTaxServiceTest {

    @InjectMocks
    private ProductTaxService service;

    @Mock
    private ProductTaxRepository repository;

    @Test
    public void postShouldSaveProductEntity() {
        when(repository.save(any())).thenReturn(getProductTaxEntity());

        assertProductTaxEntity(
                service.post(getProductTaxEntity()));
    }

    @Test
    public void insertOrUpdateShouldSaveNewProductEntity() {
        ProductTaxId id = getProductTaxId();
        ProductTaxEntity requestBody = getProductTaxEntity();

        when(repository.findById(any())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(requestBody);

        ProductTaxEntity response = service.insertOrUpdate(id, requestBody);

        assertProductTaxEntity(response);
    }

    @Test
    public void insertOrUpdateShouldUpdateExistingProductEntity() {
        ProductTaxId id = getProductTaxId();
        ProductTaxEntity requestBody = getProductTaxEntity();

        when(repository.findById(any())).thenReturn(Optional.of(requestBody));
        when(repository.save(any())).thenReturn(requestBody);

        ProductTaxEntity response = service.insertOrUpdate(id, requestBody);

        assertProductTaxEntity(response);
    }

    @Test
    public void updateShouldUpdateExistingProductEntity() {
        ProductTaxId id = getProductTaxId();
        ProductTaxEntity requestBody = getProductTaxEntity();

        when(repository.findById(id)).thenReturn(Optional.of(requestBody));
        when(repository.save(any())).thenReturn(requestBody);

        ProductTaxEntity response = service.update(id, requestBody);

        assertProductTaxEntity(response);
    }

    @Test
    public void updateShouldThrowNotFoundExceptionWhenProductNotFound() {
        ProductTaxId id = getProductTaxId();
        ProductTaxEntity requestBody = getProductTaxEntity();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.update(id, requestBody));
    }


    @Test
    public void findAllShouldReturnListOfProducts() {
        List<ProductTaxEntity> entities = Arrays.asList(getProductTaxEntity(), getProductTaxEntity());
        when(repository.findAll()).thenReturn(entities);

        List<ProductTaxEntity> response = service.findAll();

        assertEquals(entities.size(), response.size());
        assertProductTaxEntity(response.get(0));
    }

    @Test
    public void findByIdShouldReturnProductById() {
        ProductTaxId id = getProductTaxId();
        ProductTaxEntity entity = getProductTaxEntity();
        when(repository.findById(any())).thenReturn(Optional.of(entity));

        ProductTaxEntity response = service.findById(id).orElseThrow();

        assertProductTaxEntity(response);
    }

}