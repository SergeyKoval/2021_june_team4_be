package com.exadel.discount.exception;

import java.text.MessageFormat;
import java.util.UUID;

public class CouponIsAlreadyAssignedException extends RuntimeException {
    public CouponIsAlreadyAssignedException(final UUID couponId, final UUID personId) {
        super(MessageFormat.format("Coupon: {0} could not find coupon at price:{1} ", couponId, personId));
    }
}
