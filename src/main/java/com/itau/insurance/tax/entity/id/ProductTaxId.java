package com.itau.insurance.tax.entity.id;

import com.itau.insurance.tax.entity.id.base.BaseId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ProductTaxId extends BaseId {

    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

}
