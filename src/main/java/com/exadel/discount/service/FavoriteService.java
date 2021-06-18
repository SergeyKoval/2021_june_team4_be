package com.exadel.discount.service;

import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.entity.Favorite;

import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    FavoriteDto findFavoriteById(UUID id);

    void deleteFavorite(UUID id);

    List<FavoriteDto> findAllFavorites();

    FavoriteDto addFavoriteToUser(UUID userId, FavoriteDto favoriteDto);

    List<FavoriteDto> getFavoritesOfUser(UUID userId);
}
