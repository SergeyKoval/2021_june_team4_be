package com.exadel.discount.dto.favorite;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {
    @NotNull
    private UUID id;
}
