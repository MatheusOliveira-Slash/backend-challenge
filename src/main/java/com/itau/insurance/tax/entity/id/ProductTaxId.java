package com.itau.insurance.tax.entity.id;

import com.itau.insurance.tax.entity.id.base.BaseId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class ProductTaxId extends BaseId {

    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    public ProductTaxId(String id) {
        super();
        this.id = UUID.fromString(id);
    }
}
