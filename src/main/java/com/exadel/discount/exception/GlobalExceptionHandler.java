package com.exadel.discount.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails handleException(NotFoundException notFoundException) {
        log.error("Exception stack trace: ", notFoundException);

        return new ExceptionDetails(notFoundException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionDetails> handleException(
            MethodArgumentNotValidException notValidException) {
        List<ExceptionDetails> exceptionDetailsList = notValidException
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new ExceptionDetails(error.getDefaultMessage()))
                .collect(Collectors.toList());

        log.error("Exception stack trace: ", notValidException);

        return exceptionDetailsList;
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDetails handleException(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return new ExceptionDetails(exception.getMessage());
    }
}
