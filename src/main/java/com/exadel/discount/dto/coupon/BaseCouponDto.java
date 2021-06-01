package com.exadel.discount.dto.coupon;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BaseCouponDto {
    private UUID id;
    private LocalDateTime date;
}

