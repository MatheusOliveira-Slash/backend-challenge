package com.itau.insurance.tax.model;

import com.itau.insurance.tax.entity.ProductTaxEntity;
import org.junit.jupiter.api.Test;

import static com.itau.insurance.tax.Asserts.assertProductTaxEntity;
import static com.itau.insurance.tax.Asserts.assertProductTaxResponseModel;
import static com.itau.insurance.tax.Mocks.getProductTaxEntity;
import static com.itau.insurance.tax.Mocks.getProductTaxResponseModel;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTaxResponseModelTest {

    @Test
    public void toEntityShouldConvertResponseModelToEntity() {
        ProductTaxResponseModel productTaxResponseModel = getProductTaxResponseModel();
        ProductTaxEntity productTaxEntity = productTaxResponseModel.toEntity();

        assertNotNull(productTaxEntity);
        assertProductTaxEntity(productTaxEntity);
    }

    @Test
    public void fromEntityShouldConvertEntityToResponseModel() {
        ProductTaxEntity productTaxEntity = getProductTaxEntity();

        ProductTaxResponseModel productTaxResponseModel = (ProductTaxResponseModel) new ProductTaxResponseModel().fromEntity(productTaxEntity);

        assertNotNull(productTaxResponseModel);
        assertProductTaxResponseModel(productTaxResponseModel);
    }
}
