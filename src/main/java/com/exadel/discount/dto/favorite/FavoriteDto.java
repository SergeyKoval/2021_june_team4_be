package com.exadel.discount.dto.favorite;

import com.exadel.discount.dto.discount.DiscountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {
    @NotNull(message = "Favorite ID should be not null")
    private UUID id;

    @NotNull(message = "Discount should be not null")
    private DiscountDTO discountDto;
}
