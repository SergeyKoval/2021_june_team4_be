package com.exadel.discount.dto.favorite;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateFavoriteDto {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID discountId;
}