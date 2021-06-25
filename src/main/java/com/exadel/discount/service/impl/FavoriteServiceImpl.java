package com.exadel.discount.service.impl;

import com.exadel.discount.dto.favorite.CreateFavoriteDto;
import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.entity.Discount;
import com.exadel.discount.entity.Favorite;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.FavoriteMapper;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.repository.FavoriteRepository;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.service.FavoriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final FavoriteMapper favoriteMapper;
    private final DiscountRepository discountRepository;

    @Override
    public List<FavoriteDto> findAllFavorites() {
        log.debug("Getting list of all Favorites");

        List<Favorite> favoriteList = favoriteRepository.findAll();
        log.debug("Successfully got list of all Favorites");

        return favoriteMapper.toFavoriteDtoList(favoriteList);
    }

    @Override
    public FavoriteDto findFavoriteById(UUID id) {
        log.debug("Finding Favorite by ID");

        Favorite favorite = favoriteRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Favorite with id %s not found", id)));
        log.debug("Successfully Favorite is found by ID");

        return favoriteMapper.toFavoriteDto(favorite);
    }

    @Transactional
    @Override
    public FavoriteDto assignFavoriteToUser(CreateFavoriteDto createFavoriteDto) {
        log.debug("Finding of certain user by ID");

        User user = userRepository
                .findById(createFavoriteDto.getUserId())
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", createFavoriteDto.getUserId())));

        Discount discount = discountRepository
                .findById(createFavoriteDto.getDiscountId())
                .orElseThrow(() -> new NotFoundException(String.format("Discount with id %s not found", createFavoriteDto.getDiscountId())));

        log.debug("Successfully certain User and Discount are found by ID. Starting Favorite creation/saving.");

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setDiscount(discount);

        favoriteRepository.save(favorite);
        log.debug("Successfully new Favorite is saved to certain user");

        return favoriteMapper.toFavoriteDto(favorite);
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

    @Override
    public List<FavoriteDto> getFavoritesOfUser(UUID userId) {
        log.debug("Finding Favorites of certain user");

        List<FavoriteDto> FavoriteDtoList = favoriteMapper.toFavoriteDtoList(favoriteRepository.findAll()
                .stream()
                .filter(s -> s.getUser().getId().equals(userId))
                .collect(Collectors.toList()));

        log.debug("Successfully list of user's Favorites is got");

        return FavoriteDtoList;
    }
}
