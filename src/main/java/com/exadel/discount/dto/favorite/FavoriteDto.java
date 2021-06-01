package com.exadel.discount.dto.favorite;

import com.exadel.discount.dto.user.BaseUserDto;
import com.exadel.discount.entity.Favorite;
import lombok.Data;

import java.util.UUID;

@Data
public class FavoriteDto {
    private UUID id;
    private BaseUserDto baseUserDto;


    public static FavoriteDto from(Favorite favorite) {
        FavoriteDto favoriteDto = new FavoriteDto();
        favoriteDto.setId(favorite.getId());
        favoriteDto.setBaseUserDto(BaseUserDto.from(favorite.getUser()));
        return favoriteDto;
    }
}
