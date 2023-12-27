package com.itau.insurance.tax.model;

import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.model.ProductTaxModel;
import org.junit.jupiter.api.Test;

import static com.itau.insurance.tax.Asserts.assertProductTaxEntity;
import static com.itau.insurance.tax.Asserts.assertProductTaxModel;
import static com.itau.insurance.tax.Mocks.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTaxModelTest {

    @Test
    public void toEntityShouldConvertModelToEntity() {
        ProductTaxModel productTaxModel = getProductTaxModel();
        ProductTaxEntity productTaxEntity = productTaxModel.toEntity();

        assertNotNull(productTaxEntity);
        productTaxEntity.setId(getProductTaxId());
        assertProductTaxEntity(productTaxEntity);
    }

    @Test
    public void fromEntityShouldConvertEntityToModel() {
        ProductTaxModel productTaxModel = (ProductTaxModel) new ProductTaxModel().fromEntity(getProductTaxEntity());

        assertNotNull(productTaxModel);
        assertProductTaxModel(productTaxModel);
    }

}
