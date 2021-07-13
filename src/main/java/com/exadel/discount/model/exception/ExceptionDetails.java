package com.exadel.discount.model.exception;

import com.exadel.discount.controller.GlobalExceptionHandler;
import lombok.Builder;
import lombok.Data;

/**
 * This class is used for exception information that are transferred to the client side.
 *
 * @see GlobalExceptionHandler
 */

@Data
@Builder
public class ExceptionDetails {
    private String message;
    private String field;
    private ExceptionCause cause;
}
