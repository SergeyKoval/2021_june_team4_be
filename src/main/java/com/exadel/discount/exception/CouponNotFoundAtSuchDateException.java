package com.exadel.discount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.LocalDateTime;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CouponNotFoundAtSuchDateException extends RuntimeException {
    public CouponNotFoundAtSuchDateException(LocalDateTime date) {
        super(MessageFormat.format("Could not find order at Date:{0} ", date));
    }
}

