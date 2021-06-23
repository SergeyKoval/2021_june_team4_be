package com.exadel.discount.dto.favorite;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class FavoriteDto {
    @NotNull
    private UUID id;
}
