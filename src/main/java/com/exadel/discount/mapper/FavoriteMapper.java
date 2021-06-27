package com.exadel.discount.mapper;

import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.entity.Favorite;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {
    Favorite toFavorite(FavoriteDto favoriteDto);

    FavoriteDto toFavoriteDto(Favorite favorite);

    List<FavoriteDto> toFavoriteDtoList(List<Favorite> favorites);
}
