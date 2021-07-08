package com.exadel.discount.dto.discount;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class DiscountFilter {
    private List<UUID> vendorIds;
    private List<UUID> categoryIds;
    private List<UUID> countryIds;
    private List<UUID> cityIds;
    private List<UUID> tagIds;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private Boolean archived;
}
