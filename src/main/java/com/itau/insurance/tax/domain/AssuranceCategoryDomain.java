package com.itau.insurance.tax.domain;

import com.itau.insurance.tax.exception.BadRequestException;
import lombok.SneakyThrows;

import java.util.Arrays;

public enum AssuranceCategoryDomain {
    LIFE("VIDA"),
    AUTO("AUTO"),
    TRAVEL("VIAGEM"),
    RESIDENTIAL("RESIDENCIAL"),
    PROPERTY("PATRIMONIAL");
    private String value;

    AssuranceCategoryDomain(String value) {
        this.value = value;
    }

    @SneakyThrows
    public static AssuranceCategoryDomain fromValue(String value) {
        return Arrays.stream(AssuranceCategoryDomain.values())
                .filter(category -> category.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Categoria Desconhecida", value));
    }

}