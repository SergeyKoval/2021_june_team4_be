package com.exadel.discount.model.exception;

import com.exadel.discount.controller.GlobalExceptionHandler;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * This class is used for exception information that are transferred to the client side.
 * It is used for exceptions that are related to fields.
 *
 * @see GlobalExceptionHandler
 * @see ExceptionDetails
 */

@Getter
@SuperBuilder
public class FieldExceptionDetails extends ExceptionDetails {
    protected final String[] field;
}
