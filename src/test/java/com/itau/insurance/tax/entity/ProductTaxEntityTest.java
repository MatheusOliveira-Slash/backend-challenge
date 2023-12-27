package com.itau.insurance.tax.entity;

import com.itau.insurance.tax.domain.AssuranceCategoryTaxDomain;
import com.itau.insurance.tax.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.itau.insurance.tax.Asserts.assertProductTaxEntity;
import static com.itau.insurance.tax.Mocks.getProductTaxEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTaxEntityTest {

    @Test
    public void getActualValueShouldReturnCategoryCalculatedValueWhenActualValueIsNull() {
        ProductTaxEntity product = getProductTaxEntity();
        assertEquals(BigDecimal.valueOf(103.2).setScale(2, RoundingMode.UP), product.getActualValue());
    }

    @Test
    public void patchShouldUpdateFieldsCorrectly() {
        ProductTaxEntity original = getProductTaxEntity();
        ProductTaxEntity updated = new ProductTaxEntity();

        updated.setName("Updated Name");
        updated.setCategory(AssuranceCategoryTaxDomain.AUTO);
        updated.setBaseValue(BigDecimal.valueOf(200.0));

        original.patch(updated);

        assertEquals("Updated Name", original.getName());
        assertEquals(AssuranceCategoryTaxDomain.AUTO, original.getCategory());
        assertEquals(BigDecimal.valueOf(200.0), original.getBaseValue());
    }

    @Test
    public void patchShouldThrowBadRequestExceptionWhenParsingFails() {
        ProductTaxEntity original = getProductTaxEntity();

        assertThrows(BadRequestException.class, () -> original.patch(null));
    }

    @Test
    public void patchShouldNotUpdateFieldsWhenNull() {
        ProductTaxEntity original = getProductTaxEntity();
        ProductTaxEntity updated = new ProductTaxEntity();

        original.patch(updated);

        assertProductTaxEntity(original);
    }

}
