package com.exadel.discount.model.exception;

import com.exadel.discount.controller.GlobalExceptionHandler;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * This class is used for exception information that are transferred to the client side.
 *
 * @see GlobalExceptionHandler
 */

@Getter
@SuperBuilder
public class ExceptionDetails {
    protected final String message;
    protected final ExceptionCause cause;
}
