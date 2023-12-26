package com.itau.insurance.tax.entity;

import com.itau.insurance.tax.domain.AssuranceCategoryTaxDomain;
import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.ProductTaxId;
import com.itau.insurance.tax.exception.BadRequestException;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

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
    public void patch(Map<String, Object> updates) {

        try {

            if (updates.containsKey("nome"))
                this.name = (String) updates.get("nome");

            if (updates.containsKey("preco_base"))
                this.baseValue = BigDecimal.valueOf((Double) updates.get("preco_base"));

            if (updates.containsKey("categoria"))
                this.category = AssuranceCategoryTaxDomain.fromValue((String) updates.get("categoria"));

        } catch (Exception e){
            throw new BadRequestException("Erro ao parsear atualizações", "");
        }

    }


}
