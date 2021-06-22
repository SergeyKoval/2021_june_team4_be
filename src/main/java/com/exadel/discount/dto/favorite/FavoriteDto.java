package com.exadel.discount.dto.favorite;

import com.exadel.discount.dto.validation.Create;
import lombok.Data;

import javax.validation.constraints.Null;
import java.util.UUID;

@Data
public class FavoriteDto {
    @Null(groups = Create.class, message = "Favorite id should be null")
    private UUID id;
}
