package com.exadel.discount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CouponNotFoundAtSerialNumberException extends RuntimeException {
    public CouponNotFoundAtSerialNumberException(String serialNumber) {
        super(MessageFormat.format("Could not find order at serialNumber:{0} ", serialNumber));
    }
}

