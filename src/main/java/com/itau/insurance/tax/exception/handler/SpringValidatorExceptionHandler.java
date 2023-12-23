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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class SpringValidatorExceptionHandler extends GeneralExceptionHandler{

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<MessageError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){

        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.joining(" - "));

        return new ResponseEntity<>(
                new MessageError(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<MessageError> httpMessageNotReadableHandler(HttpMessageNotReadableException e){
        return new ResponseEntity<>(
                new MessageError(e.getMessage()), HttpStatus.BAD_REQUEST);   // TODO Limpar msg
    }

}