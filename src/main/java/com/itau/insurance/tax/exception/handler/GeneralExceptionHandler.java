package com.itau.insurance.tax.exception.handler;

import com.itau.insurance.tax.exception.BadRequestException;
import com.itau.insurance.tax.exception.NotFoundException;
import com.itau.insurance.tax.exception.error.MessageError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class GeneralExceptionHandler {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<MessageError> badRequestExceptionHandler(BadRequestException e){
        return new ResponseEntity<>(
                new MessageError(e.getMessage(), e.getKey()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<MessageError> notFoundExceptionHandler(NotFoundException e){
        return new ResponseEntity<>(
                new MessageError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> generalExceptionHandler(Exception e){

        log.error("Not mapped error: " + e.getMessage());

        return new ResponseEntity<>(
                new MessageError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}