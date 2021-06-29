package com.exadel.discount.controller;

import com.exadel.discount.dto.favorite.CreateFavoriteDto;
import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.service.FavoriteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @GetMapping
    @ApiOperation("Get list of all favorites with sorting")
    /**  Get list of all users with sorting by params
     sortDirection - ASC or DSC (unsorted - by default) ;
     sortField - name of sorted field by (id - by default) **/
    public List<FavoriteDto> getAllFavorites(@RequestParam(value = "sortDirection", defaultValue = "") String sortDirection,
                                         @RequestParam(value = "sortField", defaultValue = "id") String sortField){
        return favoriteService.findAllFavorites(sortDirection, sortField);
    }

    @GetMapping("{id}")
    @ApiOperation("Get favorite by ID")
    public FavoriteDto getFavoriteById(@PathVariable @NotNull final UUID id) {
        return favoriteService.findFavoriteById(id);
    }

    @PostMapping
    @ApiOperation("Save new favorite")
    public FavoriteDto addFavorite(@RequestBody @NotNull final CreateFavoriteDto createFavoriteDto) {
        return favoriteService.assignFavoriteToUser(createFavoriteDto);
    }

    @GetMapping("/ofuser")
    @ApiOperation("Get favorites of certain user")
    public List<FavoriteDto> getFavoritesOfUser(@RequestParam("userId") @NotNull final UUID userId) {
        return favoriteService.getFavoritesOfUser(userId);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete favorite by ID")
    public void deleteFavorite(@PathVariable @NotNull final UUID id) {
        favoriteService.deleteFavoriteByID(id);
    }
}

