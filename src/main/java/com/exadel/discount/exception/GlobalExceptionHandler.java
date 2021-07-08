package com.exadel.discount.exception;

import com.exadel.discount.exception.custom_exception.APIException;
import com.exadel.discount.exception.custom_exception.DeletionRestrictedException;
import com.exadel.discount.exception.custom_exception.InvalidTokenException;
import com.exadel.discount.exception.custom_exception.NotFoundException;
import com.exadel.discount.exception.type.ExceptionCause;
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

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails handleNotFoundValue(APIException exception) {
        log.error("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(ExceptionCause.NOT_FOUND_VALUE)
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
                                .cause(ExceptionCause.INCORRECT_VALUE)
                                .field(error.getField())
                                .build())
                .collect(Collectors.toList());

        log.error("Exception stack trace: ", exception);

        return exceptionDetailsList;
    }

    @ExceptionHandler({InternalAuthenticationServiceException.class, BadCredentialsException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDetails handleIncorrectAuthentication(APIException exception) {
        log.error("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(ExceptionCause.AUTHENTICATION_FAILED)
                .build();
    }

    @ExceptionHandler({AccessDeniedException.class, InvalidTokenException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDetails handleIncorrectEndpointAccessRights(APIException exception) {
        log.error("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(ExceptionCause.ACCESS_DENIED)
                .build();
    }

    @ExceptionHandler(DeletionRestrictedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ExceptionDetails handleIncorrectDataDeletion(APIException exception) {
        log.error("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(ExceptionCause.DELETION_DENIED)
                .build();
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDetails handleUncaughtException(Exception exception) {
        log.error("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(ExceptionCause.UNCAUGHT_EXCEPTION)
                .build();
    }
}