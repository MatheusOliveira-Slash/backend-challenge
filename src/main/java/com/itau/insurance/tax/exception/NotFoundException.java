package com.itau.insurance.tax.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception {
    private final String key;

    public NotFoundException(String message, String key) {
        super(message);
        this.key = key;
    }
}
