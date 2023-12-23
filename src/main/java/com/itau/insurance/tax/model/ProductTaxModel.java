package com.itau.insurance.tax.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.insurance.tax.domain.AssuranceCategoryDomain;
import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.model.base.BaseModel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductTaxModel implements BaseModel<ProductTaxEntity> {

    @NotBlank
    private String nome;

    @NotBlank
    private String categoria;

    @DecimalMin("0.0")
    private BigDecimal preco_base;

    public ProductTaxEntity toEntity() {
        ProductTaxEntity entity =  new ProductTaxEntity();

        entity.setName(getNome());
        entity.setBase_value(getPreco_base());
        entity.setCategory(AssuranceCategoryDomain.fromValue(getCategoria()));

        return entity;
    }

    public BaseModel fromEntity(ProductTaxEntity entity) {

        setNome(entity.getName());
        setPreco_base(entity.getBase_value());
        setCategoria(entity.getCategory().getValue());

        return this;
    }
}
