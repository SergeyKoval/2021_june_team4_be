package com.exadel.discount.dto.coupon;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {
    @NotNull
    private UUID id;
    @NotNull
    private LocalDateTime date;
}
