package com.exadel.discount.dto.favorite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFavoriteDto {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID discountId;
}
