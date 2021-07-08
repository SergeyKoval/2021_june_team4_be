package com.exadel.discount.dto.coupon;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class CouponFilter {
    private UUID userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
