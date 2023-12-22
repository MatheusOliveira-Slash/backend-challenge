package com.itau.insurance.tax.exception;

public class BadRequestException extends Exception {
    private final String key;

    public BadRequestException(String message, String key) {
        super(message);
        this.key = key;
    }
}