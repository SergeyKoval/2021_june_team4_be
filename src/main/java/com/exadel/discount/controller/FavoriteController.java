package com.exadel.discount.controller;

import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @GetMapping
    public List<FavoriteDto> getAllFavorites() {
        return favoriteService.findAllFavorites();
    }

    @GetMapping("{id}")
    public FavoriteDto getFavoriteById(@PathVariable final UUID id) {
        return favoriteService.findFavoriteById(id);
    }

    @GetMapping("ofuser/{userId}")
    public List<FavoriteDto> getFavoritesOfUser(@PathVariable final UUID userId) {
        return favoriteService.getFavoritesOfUser(userId);
    }

    @DeleteMapping("{id}")
    public void deleteFavorite(@PathVariable final UUID id) {
        favoriteService.deleteFavorite(id);
    }

    @PostMapping("{userId}")
    public FavoriteDto addFavorite(@PathVariable final UUID userId, FavoriteDto favoriteDto) {
        return favoriteService.addFavoriteToUser(userId, favoriteDto);
    }
}

