package com.exadel.discount.exception;

import java.text.MessageFormat;

public class CouponNotFoundAtSerialNumberException extends RuntimeException {

    public CouponNotFoundAtSerialNumberException(String serialNumber) {
        super(MessageFormat.format("Could not find order at serialNumber:{0} ", serialNumber));
    }
}

