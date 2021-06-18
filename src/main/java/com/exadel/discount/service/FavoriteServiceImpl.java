package com.exadel.discount.service;

import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.entity.Favorite;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.FavoriteNotFoundException;
import com.exadel.discount.exception.UserNotFoundException;
import com.exadel.discount.mapper.FavoriteMapper;
import com.exadel.discount.repository.FavoriteRepository;
import com.exadel.discount.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final FavoriteMapper favoriteMapper;

    @Transactional
    @Override
    public FavoriteDto addFavoriteToUser(UUID userId, FavoriteDto favoriteDto) {
        User user = userRepository.findById(userId);
        Favorite favorite = favoriteMapper.toFavorite(favoriteDto);
        user.addFavorite(favoriteMapper.toFavorite(favoriteDto));
        favorite.setUser(user);
        favoriteRepository.save(favorite);
        return favoriteMapper.toFavoriteDto(favorite);
    }

    @Override
    public FavoriteDto findFavoriteById(UUID id) {
        return favoriteRepository
                .findById(id)
                .map(favoriteMapper::toFavoriteDto);
    }

    @Override
    public List<FavoriteDto> findAllFavorites() {
        List<Favorite> favoriteList = favoriteRepository.findAll();
        return favoriteMapper.toFavoriteDtoList(favoriteList);
    }

    @Transactional
    @Override
    public void deleteFavorite(UUID id) {
        Favorite favorite = favoriteRepository.findById(id);
        favorite.getUser().removeFavorite(favorite);
        favoriteRepository.deleteById(id);
    }

    @Override
    public List<FavoriteDto> getFavoritesOfUser(UUID userId) {
        User user = userRepository.findById(userId);
        List<Favorite> favoriteList = user.getFavorites();
        return favoriteMapper.toFavoriteDtoList(favoriteList);
    }
}
