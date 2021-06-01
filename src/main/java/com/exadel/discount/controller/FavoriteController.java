package com.exadel.discount.controller;

import com.exadel.discount.dto.favorite.BaseFavoriteDto;
import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.entity.Favorite;
import com.exadel.discount.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("{userId}")
    public ResponseEntity<FavoriteDto> addFavorite(@PathVariable final UUID userId,
                                                     final BaseFavoriteDto baseFavoriteDto) {
        Favorite favorite = favoriteService.addFavoriteToUser(userId, Favorite.from(baseFavoriteDto));
        return new ResponseEntity<>(FavoriteDto.from(favorite), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FavoriteDto>> getAllFavorites() {
        List<Favorite> allFavorites = favoriteService.findAllFavorites();
        List<FavoriteDto> allFavoritesDto = allFavorites.stream()
                .map(FavoriteDto::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allFavoritesDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<FavoriteDto> getFavoriteById(@PathVariable final UUID id) {
        Favorite favorite = favoriteService.findFavoriteById(id);
        return new ResponseEntity<>(FavoriteDto.from(favorite), HttpStatus.OK);
    }

    @GetMapping("ofuser/{userId}")
    public ResponseEntity<List<FavoriteDto>> getFavoritesOfUser(@PathVariable final UUID userId) {
        List<Favorite> favoritesOfUser = favoriteService.getFavoritesOfUser(userId);
        List<FavoriteDto> favoritesOfUserDto = favoritesOfUser.stream()
                .map(FavoriteDto::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(favoritesOfUserDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<FavoriteDto>> deleteFavorite(@PathVariable final UUID id) {
        List<Favorite> remaindedFavorites = favoriteService.deleteFavorite(id);
        List<FavoriteDto> remaindedFavoritesDto = remaindedFavorites.stream()
                .map(FavoriteDto::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(remaindedFavoritesDto, HttpStatus.OK);
    }
}

