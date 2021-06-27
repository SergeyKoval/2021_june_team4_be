package com.exadel.discount.service;

import com.exadel.discount.dto.favorite.CreateFavoriteDto;
import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.entity.Favorite;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    FavoriteDto findFavoriteById(UUID id);

    void deleteFavoriteByID(UUID id);

    List<FavoriteDto> findAllFavorites(Sort sort);

    FavoriteDto assignFavoriteToUser(CreateFavoriteDto createFavoriteDto);

    List<FavoriteDto> getFavoritesOfUser(UUID userId);
}
