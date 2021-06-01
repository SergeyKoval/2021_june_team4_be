package com.exadel.discount.service;

import com.exadel.discount.entity.Favorite;

import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    Favorite findFavoriteById(UUID id);

    List<Favorite> deleteFavorite(UUID id);

    List<Favorite> findAllFavorites();

    Favorite addFavoriteToUser(UUID userId, Favorite coupon);

    List<Favorite> getFavoritesOfUser(UUID userId);
}
