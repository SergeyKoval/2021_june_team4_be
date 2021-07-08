package com.exadel.discount.mapper;

import com.exadel.discount.dto.favorite.FavoriteDTO;
import com.exadel.discount.entity.Favorite;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FavoriteMapper {

    @Autowired
    public DiscountMapper discountMapper;

    public FavoriteDTO toFavoriteDTO(Favorite favorite){
        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setId(favorite.getId());
        favoriteDTO.setDiscountDTO(discountMapper.getDTO(favorite.getDiscount()));
        return favoriteDTO;
    }
    public  abstract List<FavoriteDTO> toFavoriteDTOList(List<Favorite> favorites);
}
