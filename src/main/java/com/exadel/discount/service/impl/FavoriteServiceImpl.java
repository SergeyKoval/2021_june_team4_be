package com.exadel.discount.service.impl;

import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.entity.Favorite;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.FavoriteMapper;
import com.exadel.discount.repository.FavoriteRepository;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final FavoriteMapper favoriteMapper;

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
    public FavoriteDto addFavoriteToUser(UUID userId, FavoriteDto favoriteDto) {
        log.debug("Finding of certain user by ID");

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));

        log.debug("Successfully user is found by ID");

        Favorite favorite = favoriteMapper.toFavorite(favoriteDto);
        log.debug("Saving new Favorite to certain user");

        user.addFavorite(favoriteMapper.toFavorite(favoriteDto));
        favorite.setUser(user);
        favoriteRepository.save(favorite);
        log.debug("Successfully new Favorite is saved to certain user");

        return favoriteMapper.toFavoriteDto(favorite);
    }

    @Transactional
    @Override
    public void deleteFavoriteByID(UUID id) {
        log.debug("Deleting Favorite by ID");

        Favorite favorite = favoriteRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Favorite with id %s not found", id)));

        favorite.getUser().removeFavorite(favorite);
        favoriteRepository.deleteById(id);
        log.debug("Successfully Favorite is deleted  by ID");
    }

    @Override
    public List<FavoriteDto> getFavoritesOfUser(UUID userId) {
        log.debug("Finding of certain user by ID");

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));

        log.debug("Successfully user is found by ID");

        log.debug("Getting list of user's Favorites");

        List<Favorite> favoriteList = user.getFavorites();
        log.debug("Successfully list of user's Favorites is got");

        return favoriteMapper.toFavoriteDtoList(favoriteList);
    }
}
