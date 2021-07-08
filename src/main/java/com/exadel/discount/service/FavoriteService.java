package com.exadel.discount.service;

import com.exadel.discount.dto.favorite.CreateFavoriteDTO;
import com.exadel.discount.dto.favorite.FavoriteDTO;
import com.exadel.discount.dto.favorite.FavoriteFilter;


import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    FavoriteDTO findFavoriteById(UUID id);

    void deleteFavoriteByID(UUID id);

    FavoriteDTO assignFavoriteToUser(CreateFavoriteDTO createFavoriteDTO);

    List<FavoriteDTO> findAllFavorites(int pageNumber,
                                       int pageSize,
                                       String sortDirection,
                                       String sortField,
                                       FavoriteFilter filter);
}
