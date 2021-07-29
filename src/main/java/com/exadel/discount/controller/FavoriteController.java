package com.exadel.discount.controller;

import com.exadel.discount.model.dto.discount.DiscountDTO;
import com.exadel.discount.model.dto.favorite.FavoriteDTO;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.security.annotation.UserAccess;
import com.exadel.discount.service.FavoriteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping
    @ApiOperation("Get page-list of all favorites with filtering/sorting")
    @UserAccess
    public List<DiscountDTO> getAllFavorites(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                             @RequestParam(required = false, defaultValue = "10") int pageSize,
                                             @RequestParam(required = false, defaultValue = "") String sortDirection,
                                             @RequestParam(required = false, defaultValue = "id") String sortField,
                                             @RequestParam UUID userId) {
        return favoriteService.getAll(pageNumber, pageSize, sortDirection, sortField, userId);
    }

    @GetMapping("/search")
    @ApiOperation("Get Favorites by search text")
    @UserAccess
    public List<FavoriteDTO> search(@RequestParam(defaultValue = "8", required = false) Integer size,
                                    @RequestParam String searchText) {
        return favoriteService.search(size, searchText);
    }

    @GetMapping("{id}")
    @ApiOperation("Get favorite by ID")
    @AdminAccess
    public FavoriteDTO getFavoriteById(@PathVariable @NotNull final UUID id) {
        return favoriteService.findFavoriteById(id);
    }

    @PostMapping
    @ApiOperation("Save new favorite to user")
    @UserAccess
    public DiscountDTO addFavorite(@RequestParam @NotNull final UUID discountId) {
        return favoriteService.assignFavoriteToUser(discountId);
    }

    @DeleteMapping("{discountId}")
    @ApiOperation("Delete favorite by ID")
    @UserAccess
    public DiscountDTO deleteFavorite(@PathVariable @NotNull final UUID discountId) {
        return favoriteService.deleteFavoriteByDiscountID(discountId);
    }
}

