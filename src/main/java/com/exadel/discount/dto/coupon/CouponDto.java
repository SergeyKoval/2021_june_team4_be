package com.exadel.discount.dto.coupon;

import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CouponDto {
    @NotNull
    private UUID id;
    @NotNull
    private LocalDateTime date;
}
