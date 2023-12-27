package com.itau.insurance.tax;

import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.model.ProductTaxModel;
import com.itau.insurance.tax.model.ProductTaxResponseModel;

import static com.itau.insurance.tax.Mocks.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Asserts {

    public static void assertProductTaxEntity(ProductTaxEntity productTaxEntity) {

        assertEquals(getProductTaxEntity().getName(), productTaxEntity.getName());
        assertEquals(getProductTaxEntity().getBaseValue(), productTaxEntity.getBaseValue());
        assertEquals(getProductTaxEntity().getCategory(), productTaxEntity.getCategory());
        assertEquals(getProductTaxEntity().getId().getId(), productTaxEntity.getId().getId());
        assertEquals(getProductTaxEntity().getActualValue(), productTaxEntity.getActualValue());
    }

    public static void assertProductTaxModel(ProductTaxModel productTaxModel) {

        assertEquals(getProductTaxModel().getNome(), productTaxModel.getNome());
        assertEquals(getProductTaxModel().getPrecoBase(), productTaxModel.getPrecoBase());
        assertEquals(getProductTaxModel().getCategoria(), productTaxModel.getCategoria());
    }

    public static void assertProductTaxResponseModel(ProductTaxResponseModel productTaxResponseModel) {

        assertProductTaxModel(productTaxResponseModel);

        assertEquals(getProductTaxResponseModel().getId(), productTaxResponseModel.getId());
        assertEquals(getProductTaxResponseModel().getPrecoTarifado(), productTaxResponseModel.getPrecoTarifado());
    }
}
