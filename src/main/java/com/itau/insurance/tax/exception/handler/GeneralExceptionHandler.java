package com.itau.insurance.tax.exception.handler;

import com.itau.insurance.tax.exception.BadRequestException;
import com.itau.insurance.tax.exception.NotFoundException;
import com.itau.insurance.tax.exception.error.MessageError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class GeneralExceptionHandler {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<MessageError> badRequestExceptionHandler(BadRequestException e){

        log.error("Validation error {} with key {}", e.getMessage(), e.getKey());

        return new ResponseEntity<>(
                new MessageError(e.getMessage(), e.getKey()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<MessageError> notFoundExceptionHandler(NotFoundException e){

        log.error("Record not found for key {}", e.getKey());

        return new ResponseEntity<>(
                new MessageError(e.getMessage(), e.getKey()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> generalExceptionHandler(Exception e){

        log.error("Not mapped error: " + e.getMessage());

        return new ResponseEntity<>(
                new MessageError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}