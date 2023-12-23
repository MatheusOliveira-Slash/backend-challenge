package com.itau.insurance.tax.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.insurance.tax.domain.AssuranceCategoryDomain;
import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductTaxResponseModel extends ProductTaxModel {

    private String id;

    private BigDecimal preco_tarifado;

    @Override
    public ProductTaxEntity toEntity() {
        ProductTaxEntity entity =  new ProductTaxEntity();

        entity.setName(getNome());
        entity.setBase_value(getPreco_base());
        entity.setCategory(AssuranceCategoryDomain.fromValue(getCategoria()));
        entity.setId(getId());
        entity.setActual_value(getPreco_tarifado());

        return entity;
    }

    @Override
    public BaseModel fromEntity(ProductTaxEntity entity) {

        ProductTaxResponseModel response = (ProductTaxResponseModel) super.fromEntity(entity);
        response.setId(getId());
        response.setPreco_tarifado(getPreco_tarifado());

        return response;
    }
}
