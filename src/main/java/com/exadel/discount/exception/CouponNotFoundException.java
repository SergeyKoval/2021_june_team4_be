package com.exadel.discount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CouponNotFoundException extends RuntimeException {
    public CouponNotFoundException(UUID id) {
        super(MessageFormat.format("Could not find Coupon with id:{0}", id));
    }
}
