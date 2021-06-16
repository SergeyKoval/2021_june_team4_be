package com.exadel.discount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleException(NotFoundException notFoundException) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(notFoundException.getMessage(), notFoundException.getCode());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
}
