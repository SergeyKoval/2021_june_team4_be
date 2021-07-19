package com.exadel.discount.controller;

import com.exadel.discount.exception.APIException;
import com.exadel.discount.exception.DeletionRestrictedException;
import com.exadel.discount.exception.InvalidTokenException;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.model.exception.ExceptionCause;
import com.exadel.discount.model.exception.ExceptionDetails;
import com.exadel.discount.model.exception.FieldExceptionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.wrap;
import static org.apache.commons.lang3.StringUtils.SPACE;

/**
 * This class handles exceptions that were written in {@link ExceptionHandler}
 * and give a JSON response with the caught error to the client side.
 *
 * @see ExceptionCause
 * @see com.exadel.discount.controller
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * This method handles a problem at the level of database data searching
     * about the values that were not found in a database expect for authentication searching that is handled by
     * {@link #handleIncorrectAuthentication(AuthenticationException)}
     *
     * @param exception take a caught exception in {@link ExceptionHandler}.
     * @return the {@link ExceptionDetails} class with default message
     * and an appropriate logic word {@link ExceptionCause#NOT_FOUND_VALUE}.
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails handleNotFoundValue(APIException exception) {
        log.info("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(ExceptionCause.NOT_FOUND_VALUE)
                .build();
    }

    /**
     * This method handles a problem at the level of the validation about an incorrect data and its format.
     *
     * @param exception take a caught exception in {@link ExceptionHandler}.
     * @return the {@link FieldExceptionDetails} class with default message,
     * with the fields that causes the exception
     * and an appropriate logic word {@link ExceptionCause#INCORRECT_VALUE}.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FieldExceptionDetails handleIncorrectValue(BindException exception) {
        log.info("Exception stack trace: ", exception);

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return FieldExceptionDetails.builder()
                .message(fieldErrors
                        .stream()
                        .map(fieldError ->
                                wrap(fieldError.getField(), "'") +
                                        SPACE +
                                        fieldError.getDefaultMessage())
                        .collect(Collectors.joining(", ")))
                .field(fieldErrors
                        .stream()
                        .map(FieldError::getField)
                        .toArray(String[]::new))
                .cause(ExceptionCause.INCORRECT_VALUE)
                .build();
    }

    /**
     * This method handles a problem at the level of authentication about an incorrect data.
     *
     * @param exception take a caught exception in {@link ExceptionHandler}.
     * @return the {@link ExceptionDetails} class with default message
     * and an appropriate logic word {@link ExceptionCause#AUTHENTICATION_FAILED}.
     */
    @ExceptionHandler({InternalAuthenticationServiceException.class, BadCredentialsException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDetails handleIncorrectAuthentication(AuthenticationException exception) {
        log.info("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(ExceptionCause.AUTHENTICATION_FAILED)
                .build();
    }

    /**
     * This method handles a problem at the level of token validation about
     * the observance of the conformance of the security policy.
     *
     * @param exception take a caught exception in {@link ExceptionHandler}.
     * @return the {@link ExceptionDetails} class with default message
     * and an appropriate logic word {@link ExceptionCause#ACCESS_DENIED}.
     */
    @ExceptionHandler({AccessDeniedException.class, InvalidTokenException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDetails handleIncorrectEndpointAccessRightsThroughJWT(APIException exception) {
        log.info("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(ExceptionCause.ACCESS_DENIED)
                .build();
    }

    /**
     * This method handles a problem at the level of database data deletion about
     * data that cannot be deleted because of some restrictions at the level of a database.
     *
     * @param exception take a caught exception in {@link ExceptionHandler}.
     * @return the {@link ExceptionDetails} class with default message
     * and an appropriate logic word {@link ExceptionCause#DELETION_DENIED}.
     */
    @ExceptionHandler(DeletionRestrictedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ExceptionDetails handleIncorrectDataDeletion(APIException exception) {
        log.info("Exception stack trace: ", exception);

        return ExceptionDetails.builder()
                .message(exception.getMessage())
                .cause(ExceptionCause.DELETION_DENIED)
                .build();
    }

    /**
     * This method handles a problem at the level of all the server about
     * unforeseen exceptions that is not caught at any handler method.
     *
     * @param exception take an exception that was not caught in other methods in this class.
     * @return the {@link ExceptionDetails} class with default message
     * and an appropriate logic word {@link ExceptionCause#UNCAUGHT_EXCEPTION}.
     */

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