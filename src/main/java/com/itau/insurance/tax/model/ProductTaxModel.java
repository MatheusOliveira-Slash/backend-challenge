package com.itau.insurance.tax.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.insurance.tax.domain.AssuranceCategoryTaxDomain;
import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.model.base.BaseModel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTaxModel implements BaseModel<ProductTaxEntity> {

    @NotBlank
    private String nome;

    @NotBlank
    private String categoria;

    @JsonProperty("preco_base")
    @DecimalMin("0.0")
    private BigDecimal precoBase;

    public ProductTaxEntity toEntity() {
        ProductTaxEntity entity =  new ProductTaxEntity();

        entity.setName(getNome());
        entity.setBaseValue(getPrecoBase());
        entity.setCategory(AssuranceCategoryTaxDomain.fromValue(getCategoria()));

        return entity;
    }

    public BaseModel fromEntity(ProductTaxEntity entity) {

        setNome(entity.getName());
        setPrecoBase(entity.getBaseValue());
        setCategoria(entity.getCategory().getValue());

        return this;
    }
}
