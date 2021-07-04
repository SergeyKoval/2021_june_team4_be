package com.exadel.discount.service;

import com.exadel.discount.dto.favorite.CreateFavoriteDto;
import com.exadel.discount.dto.favorite.FavoriteDto;


import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    FavoriteDto findFavoriteById(UUID id);

    void deleteFavoriteByID(UUID id);

    List<FavoriteDto> findAllFavorites( int pageNumber, int pageSize, String sortDirection, String sortField);

    FavoriteDto assignFavoriteToUser(CreateFavoriteDto createFavoriteDto);

    List<FavoriteDto> getFavoritesOfUser(int pageNumber, int pageSize, String sortDirection, String sortField, UUID userId);
}
