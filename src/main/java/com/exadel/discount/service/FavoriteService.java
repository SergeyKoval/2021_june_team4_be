package com.exadel.discount.service;

import com.exadel.discount.dto.favorite.CreateFavoriteDTO;
import com.exadel.discount.dto.favorite.FavoriteDTO;


import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    FavoriteDTO findFavoriteById(UUID id);

    void deleteFavoriteByID(UUID id);

    List<FavoriteDTO> findAllFavorites(int pageNumber, int pageSize, String sortDirection, String sortField);

    FavoriteDTO assignFavoriteToUser(CreateFavoriteDTO createFavoriteDTO);

    List<FavoriteDTO> getFavoritesOfUser(int pageNumber, int pageSize, String sortDirection, String sortField, UUID userId);
}
