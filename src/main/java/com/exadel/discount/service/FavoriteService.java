package com.exadel.discount.service;

import com.exadel.discount.model.dto.discount.DiscountDTO;
import com.exadel.discount.model.dto.favorite.FavoriteDTO;
import com.exadel.discount.model.dto.favorite.FavoriteFilter;


import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    FavoriteDTO findFavoriteById(UUID id);

    DiscountDTO deleteFavoriteByDiscountID(UUID discountId);

    DiscountDTO assignFavoriteToUser(UUID discountId);

    List<FavoriteDTO> getAll(int pageNumber, int pageSize, String sortDirection, String sortField,
                             FavoriteFilter filter);

    List<FavoriteDTO> search(Integer size, String searchText);
}
