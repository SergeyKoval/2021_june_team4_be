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

import static com.exadel.discount.exception.type.ExceptionCause.NOT_FOUND_VALUE;
import static com.exadel.discount.exception.type.ExceptionCause.INCORRECT_VALUE;
import static com.exadel.discount.exception.type.ExceptionCause.AUTHENTICATION_FAILED;
import static com.exadel.discount.exception.type.ExceptionCause.ACCESS_DENIED;
import static com.exadel.discount.exception.type.ExceptionCause.DELETION_DENIED;
import static com.exadel.discount.exception.type.ExceptionCause.UNCAUGHT_EXCEPTION;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails handleNotFoundValue(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(NOT_FOUND_VALUE)
                .build();
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
                        ExceptionDetails.builder()
                                .message(error.getDefaultMessage())
                                .cause(INCORRECT_VALUE)
                                .field(error.getField())
                                .build())
                .collect(Collectors.toList());

        log.error("Exception stack trace: ", exception);

        return exceptionDetailsList;
    }

    @ExceptionHandler({InternalAuthenticationServiceException.class, BadCredentialsException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDetails handleIncorrectAuthentication(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(AUTHENTICATION_FAILED)
                .build();
    }

    @ExceptionHandler({AccessDeniedException.class, InvalidTokenException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDetails handleIncorrectEndpointAccessRights(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(ACCESS_DENIED)
                .build();
    }

    @ExceptionHandler(DeletionRestrictedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ExceptionDetails handleIncorrectDataDeletion(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(DELETION_DENIED)
                .build();
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDetails handleUncaughtException(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(UNCAUGHT_EXCEPTION)
                .build();
    }
}