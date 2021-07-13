package com.exadel.discount.model.dto.coupon;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class CouponFilter {
    private UUID userId;
    private LocalDateTime creationTimeFrom;
    private LocalDateTime creationTimeTo;
    private List<UUID> vendorIds;
    private List<UUID> categoryIds;
    private List<UUID> countryIds;
    private List<UUID> cityIds;
    private List<UUID> tagIds;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private Boolean archived;
}
