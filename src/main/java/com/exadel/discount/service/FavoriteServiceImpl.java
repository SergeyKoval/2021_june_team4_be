package com.exadel.discount.service;

import com.exadel.discount.entity.Favorite;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.FavoriteNotFoundException;
import com.exadel.discount.repository.FavoriteRepository;
import com.exadel.discount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserRepository userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Favorite addFavoriteToUser(UUID userId, Favorite favorite) {
        User user = userRepository.findUserById(userId);
        user.addFavorite(favorite);
        favorite.setUser(user);
        favoriteRepository.save(favorite);
        return favorite;
    }

    @Override
    public Favorite findFavoriteById(UUID id) {
        return favoriteRepository
                .findById(id)
                .orElseThrow(() -> new FavoriteNotFoundException(id));
    }

    @Override
    public List<Favorite> findAllFavorites() {
        return StreamSupport
                .stream(favoriteRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<Favorite> deleteFavorite(UUID id) {
        Favorite favorite = findFavoriteById(id);
        favorite.getUser().removeFavorite(favorite);
        favoriteRepository.delete(favorite);
        return findAllFavorites();
    }

    @Override
    public List<Favorite> getFavoritesOfUser(UUID userId) {
        return userRepository.findUserById(userId).getFavorites();

    }
}
