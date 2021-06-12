package com.exadel.discount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Timestamp;
import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CouponNotFoundAtTimeException extends RuntimeException {
    public CouponNotFoundAtTimeException(Timestamp time) {
        super(MessageFormat.format("Could not find coupon at time:{0} ", time));
    }
}