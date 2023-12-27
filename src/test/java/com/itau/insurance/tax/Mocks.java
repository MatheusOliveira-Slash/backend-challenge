package com.itau.insurance.tax;

import com.itau.insurance.tax.domain.AssuranceCategoryTaxDomain;
import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.entity.id.ProductTaxId;
import com.itau.insurance.tax.model.ProductTaxModel;
import com.itau.insurance.tax.model.ProductTaxResponseModel;

import java.math.BigDecimal;
import java.util.UUID;

public class Mocks {

    public static final String baseId = UUID.randomUUID().toString();

    public static ProductTaxId getProductTaxId(){
        return new ProductTaxId(baseId);
    }

    public static ProductTaxEntity getProductTaxEntity(){
        return new ProductTaxEntity(getProductTaxId(), "Testes seguro", AssuranceCategoryTaxDomain.LIFE, BigDecimal.valueOf(100.0), null);
    }

    public static ProductTaxResponseModel getProductTaxResponseModel(){
        return (ProductTaxResponseModel) new ProductTaxResponseModel().fromEntity(getProductTaxEntity());
    }

    public static ProductTaxModel getProductTaxModel(){
        return (ProductTaxModel) new ProductTaxModel().fromEntity(getProductTaxEntity());
    }


}
