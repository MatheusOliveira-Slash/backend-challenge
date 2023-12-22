package com.itau.insurance.tax.model;

import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.model.base.BaseModel;

public class ProductTaxModel implements BaseModel<ProductTaxEntity> {
    public ProductTaxEntity toEntity() {
        return null;
    }

    public BaseModel fromEntity(ProductTaxEntity entity) {
        return null;
    }

    public boolean isValid() {
        return false;
    }
}
