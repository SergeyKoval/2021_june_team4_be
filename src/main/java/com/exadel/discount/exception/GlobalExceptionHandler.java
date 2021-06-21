package com.exadel.discount.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleException(NotFoundException notFoundException) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(notFoundException.getMessage());
        log.error(notFoundException.getMessage());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDetails>> handleException(
            MethodArgumentNotValidException notValidException) {
        List<ExceptionDetails> exceptionDetailsList = notValidException
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new ExceptionDetails(error.getDefaultMessage()))
                .collect(Collectors.toList());
        log.error(notValidException.getMessage());
        return new ResponseEntity<>(exceptionDetailsList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handleException(
            Exception exception) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(exception.getMessage());
        log.error(exception.getMessage());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
