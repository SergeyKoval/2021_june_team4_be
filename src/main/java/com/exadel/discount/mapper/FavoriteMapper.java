package com.exadel.discount.mapper;

import com.exadel.discount.dto.discount.DiscountDTO;
import com.exadel.discount.dto.favorite.FavoriteDTO;
import com.exadel.discount.entity.Discount;
import com.exadel.discount.entity.Favorite;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DiscountMapper.class})
public interface FavoriteMapper {
    FavoriteDTO toFavoriteDTO(Favorite favorite);

    List<FavoriteDTO> toFavoriteDTOList(List<Favorite> favorites);

    DiscountDTO getDTO(Discount discount);
}
