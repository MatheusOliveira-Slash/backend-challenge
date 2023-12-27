package com.itau.insurance.tax.entity;

import com.itau.insurance.tax.domain.AssuranceCategoryTaxDomain;
import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.ProductTaxId;
import com.itau.insurance.tax.exception.BadRequestException;
import jakarta.persistence.Entity;
import lombok.*;

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

    @SneakyThrows
    @Override
    public void patch(BaseEntity requestEntity) {

        try {

            ProductTaxEntity request = (ProductTaxEntity) requestEntity;

            if(request.getName() != null)
                this.setName(request.getName());

            if(request.getCategory() != null)
                this.setCategory(request.getCategory());

            if(request.getBaseValue() != null)
                this.setBaseValue(request.getBaseValue());

        } catch (Exception e){
            throw new BadRequestException("Erro ao parsear atualizações", "");
        }

    }


}
