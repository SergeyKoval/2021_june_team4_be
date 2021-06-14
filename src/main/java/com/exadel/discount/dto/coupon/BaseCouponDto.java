package com.exadel.discount.dto.coupon;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class BaseCouponDto {
    private UUID id;
    private Timestamp date;
}

