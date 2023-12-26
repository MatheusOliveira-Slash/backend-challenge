package com.itau.insurance.tax.domain;

import com.itau.insurance.tax.exception.BadRequestException;
import lombok.Getter;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Getter
public enum AssuranceCategoryTaxDomain {
    LIFE("VIDA", 0.01f, 0.022f, 0.0f),
    AUTO("AUTO", 0.055f, 0.04f, 0.01f),
    TRAVEL("VIAGEM", 0.02f, 0.04f, 0.01f),
    RESIDENTIAL("RESIDENCIAL", 0.04f, 0.0f, 0.03f),
    PROPERTY("PATRIMONIAL", 0.05f, 0.03f, 0.0f);
    private String value;
    private float iof;
    private float pis;
    private float cofins;

    AssuranceCategoryTaxDomain(String value, float iof, float pis, float cofins) {
        this.value = value;
        this.iof = iof;
        this.pis = pis;
        this.cofins = cofins;
    }

    @SneakyThrows
    public static AssuranceCategoryTaxDomain fromValue(String value) {
        return Arrays.stream(AssuranceCategoryTaxDomain.values())
                .filter(category -> category.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Categoria Desconhecida", value));
    }

    public BigDecimal calculateActualValue(BigDecimal baseValue) {
        BigDecimal iofValue = baseValue.multiply(BigDecimal.valueOf(iof));
        BigDecimal pisValue = baseValue.multiply(BigDecimal.valueOf(pis));
        BigDecimal cofinsValue = baseValue.multiply(BigDecimal.valueOf(cofins));

        return baseValue.add(iofValue).add(pisValue).add(cofinsValue).setScale(2, RoundingMode.UP);
    }

}