package com.exadel.discount.controller;

import com.exadel.discount.dto.favorite.CreateFavoriteDto;
import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.service.FavoriteService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("Get list of all favorites")
    public List<FavoriteDto> getAllFavorites() {
        return favoriteService.findAllFavorites();
    }

    @GetMapping("{id}")
    @ApiOperation("Get favorite by ID")
    public FavoriteDto getFavoriteById(@PathVariable final UUID id) {
        return favoriteService.findFavoriteById(id);
    }

    @PostMapping
    @ApiOperation("Save new favorite")
    public FavoriteDto addFavorite(@RequestBody final CreateFavoriteDto createFavoriteDto) {
        return favoriteService.assignFavoriteToUser(createFavoriteDto);
    }

    @GetMapping("/ofuser")
    @ApiOperation("Get favorites of certain user")
    public List<FavoriteDto> getFavoritesOfUser(@RequestParam("userId") final UUID userId) {
        return favoriteService.getFavoritesOfUser(userId);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete favorite by ID")
    public void deleteFavorite(@PathVariable final UUID id) {
        favoriteService.deleteFavoriteByID(id);
    }
}

