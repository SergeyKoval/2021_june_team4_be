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
        String message = notFoundException.getMessage();
        ExceptionDetails exceptionDetails = new ExceptionDetails(message);

        log.error("{}: {}", notFoundException.getClass().getSimpleName(), message, notFoundException);

        return exceptionDetails;
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

        log.error("{}: {}", notValidException.getClass().getSimpleName(), notValidException.getMessage(), notValidException);

        return exceptionDetailsList;
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDetails handleException(Exception exception) {
        String message = exception.getMessage();
        ExceptionDetails exceptionDetails = new ExceptionDetails(message);

        log.error("{}: {}", exception.getClass().getSimpleName(), message, exception);

        return exceptionDetails;
    }
}
