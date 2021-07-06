package com.exadel.discount.service.impl;

import com.exadel.discount.dto.favorite.CreateFavoriteDTO;
import com.exadel.discount.dto.favorite.FavoriteDTO;
import com.exadel.discount.entity.Discount;
import com.exadel.discount.entity.Favorite;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.FavoriteMapper;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.repository.FavoriteRepository;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.service.FavoriteService;
import com.exadel.discount.service.SortPageMaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public List<FavoriteDTO> findAllFavorites(int pageNumber, int pageSize, String sortDirection, String sortField) {
        Pageable paging = SortPageMaker.makePageable(pageNumber, pageSize, sortDirection, sortField);
        log.debug("Getting sorted page-list of all Favorites");

        Page<Favorite> favoriteList = favoriteRepository.findAll(paging);
        if(favoriteList.isEmpty()) throw new NotFoundException("No favorites are found");

        log.debug("Successfully got sorted page-list of all Favorites");

        return favoriteMapper.toFavoriteDTOList(favoriteList.toList());
    }

    @Override
    public FavoriteDTO findFavoriteById(UUID id) {
        log.debug("Finding Favorite by ID");

        Favorite favorite = favoriteRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Favorite with id %s not found", id)));

        log.debug("Successfully Favorite is found by ID");

        return favoriteMapper.toFavoriteDTO(favorite);
    }

    @Override
    public FavoriteDTO assignFavoriteToUser(CreateFavoriteDTO createFavoriteDTO) {
        log.debug("Finding of certain user by ID");

        User user = userRepository
                .findById(createFavoriteDTO.getUserId())
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", createFavoriteDTO.getUserId())));

        Discount discount = discountRepository
                .findById(createFavoriteDTO.getDiscountId())
                .orElseThrow(() -> new NotFoundException(String.format("Discount with id %s not found", createFavoriteDTO.getDiscountId())));

        log.debug("Successfully certain User and Discount are found by ID. Starting Favorite creation/saving.");

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setDiscount(discount);

        favoriteRepository.save(favorite);
        log.debug("Successfully new Favorite is saved to certain user");

        return favoriteMapper.toFavoriteDTO(favorite);
    }

    @Override
    public void deleteFavoriteByID(UUID id) {
        log.debug("Finding & deleting Favorite by ID");

        favoriteRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Favorite with id %s not found", id)));
        favoriteRepository.deleteById(id);
        log.debug("Successfully Favorite is deleted  by ID");
    }

    @Override
    public List<FavoriteDTO> getFavoritesOfUser(int pageNumber, int pageSize, String sortDirection, String sortField, UUID userId){
        Pageable paging = SortPageMaker.makePageable(pageNumber, pageSize, sortDirection, sortField);

        log.debug("Finding Favorites of certain user");
        List<Favorite> FavoriteList = favoriteRepository.findByUserId(userId, paging).toList();
        if(FavoriteList.isEmpty()) throw new NotFoundException(String.format("No Favorites are found at user with Id %s ", userId));
        log.debug("Successfully sorted page-list of user's Favorites is got");

        return favoriteMapper.toFavoriteDTOList(FavoriteList);
    }
}