package com.exadel.discount.dto.favorite;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FavoriteFilter {
    private UUID userId;
}
