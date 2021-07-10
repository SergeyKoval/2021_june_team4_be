package com.exadel.discount.dto.favorite;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FavoriteFilter {
    private UUID userId;
    private List<UUID> vendorIds;
    private List<UUID> categoryIds;
    private List<UUID> countryIds;
    private List<UUID> cityIds;
    private List<UUID> tagIds;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private Integer percentFrom;
    private Integer percentTo;
    private Boolean archived;
}
