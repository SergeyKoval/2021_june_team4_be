package com.exadel.discount.exception;

import com.exadel.discount.exception.type.ExceptionCause;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDetails {
    private String message;
    private String field;
    private ExceptionCause cause;
}
