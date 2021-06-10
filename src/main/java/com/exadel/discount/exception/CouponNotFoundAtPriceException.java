package com.exadel.discount.exception;

import java.text.MessageFormat;

public class CouponNotFoundAtPriceException extends RuntimeException{
    public CouponNotFoundAtPriceException(int price){
        super(MessageFormat.format("Could not find coupon at price:{0} ",price));
    }
}