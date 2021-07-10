package com.exadel.discount.controller;

import com.exadel.discount.dto.coupon.CouponFilter;
import com.exadel.discount.dto.favorite.CreateFavoriteDTO;
import com.exadel.discount.dto.favorite.FavoriteDTO;
import com.exadel.discount.dto.favorite.FavoriteFilter;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.security.annotation.UserAccess;
import com.exadel.discount.service.FavoriteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @GetMapping
    @ApiOperation("Get page-list of all favorites with filtering/sorting")
    @AdminAccess
    public List<FavoriteDTO> getAllFavorites(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                             @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "sortDirection", defaultValue = "") String sortDirection,
                                             @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                             @RequestParam(required = false) List<UUID> vendorId,
                                             @RequestParam(required = false) List<UUID> categoryId,
                                             @RequestParam(required = false) List<UUID> countryId,
                                             @RequestParam(required = false) List<UUID> cityId,
                                             @RequestParam(required = false) List<UUID> tagId,
                                             @RequestParam(required = false) Integer percentFrom,
                                             @RequestParam(required = false) Integer percentTo,
                                             @RequestParam(required = false)
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                         LocalDateTime endDateTimeFrom,
                                             @RequestParam(required = false)
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                         LocalDateTime endDateTimeTo,
                                             @RequestParam(value = "userId", required = false) UUID userId) {
        FavoriteFilter filter = FavoriteFilter.builder()
                .vendorIds(vendorId)
                .categoryIds(categoryId)
                .countryIds(countryId)
                .cityIds(cityId)
                .tagIds(tagId)
                .percentFrom(percentFrom)
                .percentTo(percentTo)
                .userId(userId)
                .endDateFrom(endDateTimeFrom)
                .endDateTo(endDateTimeTo)
                .userId(userId)
                .build();
        return favoriteService.findAllFavorites(pageNumber, pageSize, sortDirection, sortField, filter);
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
    public FavoriteDTO addFavorite(@RequestBody @NotNull final CreateFavoriteDTO createFavoriteDTO) {
        return favoriteService.assignFavoriteToUser(createFavoriteDTO);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete favorite by ID")
    @UserAccess
    public void deleteFavorite(@PathVariable @NotNull final UUID id) {
        favoriteService.deleteFavoriteByID(id);
    }
}

