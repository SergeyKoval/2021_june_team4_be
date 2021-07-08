package com.exadel.discount.dto.coupon;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class CouponFilter {
    private List<UUID> cityIds;
    private List<UUID> tagIds;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private Integer percentFrom;
    private Integer percentTo;
    private Boolean archived;
}
