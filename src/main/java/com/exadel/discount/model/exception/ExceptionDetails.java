package com.exadel.discount.model.exception;

import lombok.Builder;
import lombok.Data;

/**
 * This class is used for exception information that are transferred to the client side.
 *
 * @see com.exadel.discount.controller.exception.GlobalExceptionHandler
 */

@Data
@Builder
public class ExceptionDetails {
    private String message;
    private String field;
    private ExceptionCause cause;
}
