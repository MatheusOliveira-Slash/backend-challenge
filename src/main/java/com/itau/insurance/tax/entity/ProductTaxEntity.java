package com.itau.insurance.tax.entity;

import com.itau.insurance.tax.domain.AssuranceCategoryDomain;
import com.itau.insurance.tax.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTaxEntity extends BaseEntity {

    private String id;

    private String name;

    private AssuranceCategoryDomain category;

    private BigDecimal base_value;

    private BigDecimal actual_value;
}
