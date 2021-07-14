package com.exadel.discount.service.impl;

import com.exadel.discount.model.dto.favorite.CreateFavoriteDTO;
import com.exadel.discount.model.dto.favorite.FavoriteDTO;
import com.exadel.discount.model.dto.favorite.FavoriteFilter;
import com.exadel.discount.model.entity.Discount;
import com.exadel.discount.model.entity.Favorite;
import com.exadel.discount.model.entity.QFavorite;
import com.exadel.discount.model.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.model.dto.mapper.FavoriteMapper;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.repository.FavoriteRepository;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.repository.query.QueryPredicateBuilder;
import com.exadel.discount.service.FavoriteService;
import com.exadel.discount.repository.query.SortPageUtil;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final FavoriteMapper favoriteMapper;
    private final DiscountRepository discountRepository;

    @Override
    public List<FavoriteDTO> search(int pageNumber, int pageSize, String sortDirection, String sortField,
                                              FavoriteFilter favoriteFilter) {
        Pageable paging = SortPageUtil.makePageable(pageNumber, pageSize, sortDirection, sortField);
        log.debug("Getting sorted page-list of all Favorites");
        Page<Favorite> favoriteList = favoriteRepository
                .findAll(preparePredicateForFindingAllFavorites(favoriteFilter), paging);
        log.debug("Successfully got sorted page-list of all Favorites");
        return favoriteMapper.toFavoriteDTOList(favoriteList.toList());
    }

    @Override
    public FavoriteDTO findFavoriteById(UUID id) {
        log.debug("Finding Favorite by ID");
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Favorite with id %s not found", id)));
        log.debug("Successfully Favorite is found by ID");
        return favoriteMapper.toFavoriteDTO(favorite);
    }

    @Transactional
    @Override
    public FavoriteDTO assignFavoriteToUser(CreateFavoriteDTO createFavoriteDTO) {
        log.debug("Finding of certain user by ID");
        User user = userRepository
                .findById(createFavoriteDTO.getUserId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id %s not found", createFavoriteDTO.getUserId())));

        Discount discount = discountRepository
                .findById(createFavoriteDTO.getDiscountId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Discount with id %s not found", createFavoriteDTO.getDiscountId())));
        log.debug("Successfully certain User and Discount are found by ID. Starting Favorite creation/saving.");

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setDiscount(discount);

        Favorite favoriteSaved = favoriteRepository.save(favorite);
        log.debug("Successfully new Favorite is saved to certain user");

        return favoriteMapper.toFavoriteDTO(favoriteSaved);
    }

    @Transactional
    @Override
    public void deleteFavoriteByID(UUID id) {
        log.debug("Finding & deleting Favorite by ID");

        favoriteRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Favorite with id %s not found", id)));
        favoriteRepository.deleteById(id);
        log.debug("Successfully Favorite is deleted  by ID");
    }

    private Predicate preparePredicateForFindingAllFavorites(FavoriteFilter favoriteFilter) {
        return ExpressionUtils.and(
                QueryPredicateBuilder.init()
                        .append(favoriteFilter.getCountryIds(),
                                QFavorite.favorite.discount.vendorLocations.any().city.country.id::in)
                        .append(favoriteFilter.getCityIds(),
                                QFavorite.favorite.discount.vendorLocations.any().city.id::in)
                        .buildOr(),
                QueryPredicateBuilder.init()
                        .append(favoriteFilter.getUserId(), QFavorite.favorite.user.id::eq)
                        .append(favoriteFilter.getArchived(), QFavorite.favorite.discount.archived::eq)
                        .append(favoriteFilter.getEndDateFrom(), QFavorite.favorite.discount.endTime::goe)
                        .append(favoriteFilter.getEndDateTo(), QFavorite.favorite.discount.endTime::loe)
                        .append(favoriteFilter.getCategoryIds(), QFavorite.favorite.discount.category.id::in)
                        .append(favoriteFilter.getTagIds(), QFavorite.favorite.discount.tags.any().id::in)
                        .append(favoriteFilter.getVendorIds(), QFavorite.favorite.discount.vendor.id::in)
                        .buildAnd());
    }
}