package com.exadel.discount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CouponIsAlreadyAssignedException extends RuntimeException {
    public CouponIsAlreadyAssignedException(final UUID couponId, final UUID personId) {
        super(MessageFormat.format("Coupon: {0} could not find coupon at price:{1} ", couponId, personId));
    }
}
