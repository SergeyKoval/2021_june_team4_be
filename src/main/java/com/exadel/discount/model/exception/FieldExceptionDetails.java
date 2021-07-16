package com.exadel.discount.model.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FieldExceptionDetails extends ExceptionDetails {
    protected final String[] field;
}
