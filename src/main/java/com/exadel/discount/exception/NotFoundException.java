package com.exadel.discount.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

    private String code;

    public NotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
}
