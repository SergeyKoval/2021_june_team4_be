package com.exadel.discount.model.dto.mapper;

import com.exadel.discount.model.dto.favorite.FavoriteDTO;
import com.exadel.discount.model.entity.Favorite;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DiscountMapper.class})
public interface FavoriteMapper {

    FavoriteDTO toFavoriteDTO(Favorite favorite);

    List<FavoriteDTO> toFavoriteDTOList(List<Favorite> favorites);
}
