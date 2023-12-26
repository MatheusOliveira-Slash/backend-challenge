package com.itau.insurance.tax.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.insurance.tax.domain.AssuranceCategoryTaxDomain;
import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.model.base.BaseModel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @DecimalMin(value = "0.0", message = "preco_base deve ser maior que zero")
    @NotNull(message = "preco_base não pode ser nulo")
    @JsonProperty("preco_base")
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
