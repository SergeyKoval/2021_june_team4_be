package com.exadel.discount.exception;

import java.security.Timestamp;
import java.text.MessageFormat;

public class CouponNotFoundAtTimeException extends RuntimeException{
    public CouponNotFoundAtTimeException(Timestamp time){
        super(MessageFormat.format("Could not find coupon at time:{0} ",time));
    }
}