package com.exadel.discount.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
    public ExceptionDetails handleNotFoundException(NotFoundException exception) {
        log.error("Exception stack trace: ", exception);

        return new ExceptionDetails(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionDetails> handleMethodArgumentNotValidException(
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

    @ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDetails handleException(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return new ExceptionDetails(exception.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class, InvalidTokenException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDetails handleAccessDeniedException(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return new ExceptionDetails(exception.getMessage());
    }
      
    @ExceptionHandler(DeletionRestrictedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ExceptionDetails handleException(DeletionRestrictedException exception) {
        log.error("Exception stack trace: ", exception);

        return new ExceptionDetails(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDetails handleUncaughtException(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return new ExceptionDetails(exception.getMessage());
    }
}