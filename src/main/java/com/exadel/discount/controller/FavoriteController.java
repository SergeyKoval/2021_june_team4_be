package com.exadel.discount.controller;

import com.exadel.discount.dto.coupon.CouponFilter;
import com.exadel.discount.dto.favorite.CreateFavoriteDTO;
import com.exadel.discount.dto.favorite.FavoriteDTO;
import com.exadel.discount.dto.favorite.FavoriteFilter;
import com.exadel.discount.service.FavoriteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    @ApiOperation("Get sorted(id) page-list of all favorites")
    public List<FavoriteDTO> getAllFavorites(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                             @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "sortDirection", defaultValue = "") String sortDirection,
                                             @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                             @RequestParam(value = "userId", required = false) UUID userId) {
        FavoriteFilter filter = FavoriteFilter.builder()
                .userId(userId)
                .build();
        return favoriteService.findAllFavorites(pageNumber, pageSize, sortDirection, sortField, filter);
    }

    @GetMapping("{id}")
    @ApiOperation("Get favorite by ID")
    public FavoriteDTO getFavoriteById(@PathVariable @NotNull final UUID id) {
        return favoriteService.findFavoriteById(id);
    }

    @PostMapping
    @ApiOperation("Save new favorite to user")
    public FavoriteDTO addFavorite(@RequestBody @NotNull final CreateFavoriteDTO createFavoriteDTO) {
        return favoriteService.assignFavoriteToUser(createFavoriteDTO);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete favorite by ID")
    public void deleteFavorite(@PathVariable @NotNull final UUID id) {
        favoriteService.deleteFavoriteByID(id);
    }
}

