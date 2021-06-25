package com.exadel.discount.dto.favorite;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FavoriteDto {
    @NotNull
    private UUID id;
}
