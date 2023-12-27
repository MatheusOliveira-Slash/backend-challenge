package com.itau.insurance.tax.entity.id;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.itau.insurance.tax.Mocks.baseId;
import static com.itau.insurance.tax.Mocks.getProductTaxId;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTaxIdTest {

    @Test
    public void constructorWithParameterShouldSetIdFromString() {
        ProductTaxId productTaxId = getProductTaxId();

        assertNotNull(productTaxId.getId());
        assertEquals(UUID.fromString(baseId), productTaxId.getId());
    }

    @Test
    public void constructorWithInvalidParameterShouldThrowException() {
        String invalidIdString = "invalid-id";

        assertThrows(IllegalArgumentException.class, () -> new ProductTaxId(invalidIdString));
    }

}
