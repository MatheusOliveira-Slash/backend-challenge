package com.itau.insurance.tax.entity;

import com.itau.insurance.tax.domain.AssuranceCategoryTaxDomain;
import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.ProductTaxId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductTaxEntity extends BaseEntity<ProductTaxId> {

    private ProductTaxId id = new ProductTaxId();

    private String name;

    private AssuranceCategoryTaxDomain category;

    private BigDecimal baseValue;

    private BigDecimal actualValue;

    public BigDecimal getActualValue(){

        if(actualValue != null)
            return actualValue;

        return category.calculateActualValue(baseValue);
    }
}
