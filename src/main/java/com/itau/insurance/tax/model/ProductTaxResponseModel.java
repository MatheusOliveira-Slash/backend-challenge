package com.itau.insurance.tax.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.insurance.tax.domain.AssuranceCategoryTaxDomain;
import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.entity.id.ProductTaxId;
import com.itau.insurance.tax.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductTaxResponseModel extends ProductTaxModel {

    private String id;

    @JsonProperty("preco_tarifado")
    private BigDecimal precoTarifado;

    @Override
    public ProductTaxEntity toEntity() {
        ProductTaxEntity entity =  new ProductTaxEntity();

        entity.setName(getNome());
        entity.setBaseValue(getPrecoBase());
        entity.setId(new ProductTaxId(UUID.fromString(getId())));
        entity.setActualValue(getPrecoTarifado());
        entity.setCategory(AssuranceCategoryTaxDomain.fromValue(getCategoria()));

        return entity;
    }

    @Override
    public BaseModel fromEntity(ProductTaxEntity entity) {

        ProductTaxResponseModel response = (ProductTaxResponseModel) super.fromEntity(entity);

        response.setId(entity.getId().getId().toString());
        response.setPrecoTarifado(entity.getActualValue());

        return response;
    }
}
