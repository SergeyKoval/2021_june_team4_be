package com.exadel.discount.exception;

import com.exadel.discount.exception.custom_exception.DeletionRestrictedException;
import com.exadel.discount.exception.custom_exception.InvalidTokenException;
import com.exadel.discount.exception.custom_exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.exadel.discount.exception.type.ExceptionCause.*;
import static org.apache.commons.lang3.StringUtils.upperCase;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails handleNotFoundValue(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return new ExceptionDetails(exception.getMessage(), NOT_FOUND.name());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionDetails> handleIncorrectValue(BindException exception) {
        List<ExceptionDetails> exceptionDetailsList = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        new ExceptionDetails(
                                error.getDefaultMessage(),
                                upperCase(error.getField()) + _FILED_INCORRECT.name()))
                .collect(Collectors.toList());

        log.error("Exception stack trace: ", exception);

        return exceptionDetailsList;
    }

    @ExceptionHandler({InternalAuthenticationServiceException.class, BadCredentialsException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDetails handleIncorrectAuthentication(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return new ExceptionDetails(exception.getMessage(), AUTHENTICATION_FAILED.name());
    }

    @ExceptionHandler({AccessDeniedException.class, InvalidTokenException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDetails handleIncorrectEndpointAccessRights(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return new ExceptionDetails(exception.getMessage(), ACCESS_DENIED.name());
    }

    @ExceptionHandler(DeletionRestrictedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ExceptionDetails handleIncorrectDataDeletion(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return new ExceptionDetails(exception.getMessage(), DELETION_RESTRICTED.name());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDetails handleUncaughtException(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return new ExceptionDetails(exception.getMessage(), UNCAUGHT_EXCEPTION.name());
    }
}