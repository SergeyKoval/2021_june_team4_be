package com.exadel.discount.controller;

import com.exadel.discount.dto.discount.CreateDiscountDTO;
import com.exadel.discount.dto.discount.DiscountDTO;
import com.exadel.discount.dto.discount.DiscountFilter;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.security.annotation.UserAccess;
import com.exadel.discount.service.DiscountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping
    @ApiOperation("Get all discounts")
    @UserAccess
    public List<DiscountDTO> getAllDiscounts(@RequestParam(defaultValue = "0", required = false) Integer page,
                                             @RequestParam(defaultValue = "12", required = false) Integer size,
                                             @RequestParam(defaultValue = "id", required = false) String sortBy,
                                             @RequestParam(defaultValue = "asc", required = false) String sortDirection,
                                             @RequestParam(required = false) List<UUID> vendorId,
                                             @RequestParam(required = false) List<UUID> categoryId,
                                             @RequestParam(required = false) List<UUID> countryId,
                                             @RequestParam(required = false) List<UUID> cityId,
                                             @RequestParam(required = false) List<UUID> tagId,
                                             @RequestParam(required = false)
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                     LocalDateTime endDateTimeFrom,
                                             @RequestParam(required = false)
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                     LocalDateTime endDateTimeTo) {
        DiscountFilter filter = DiscountFilter.builder()
                .vendorIds(vendorId)
                .categoryIds(categoryId)
                .countryIds(countryId)
                .cityIds(cityId)
                .tagIds(tagId)
                .endDateFrom(endDateTimeFrom)
                .endDateTo(endDateTimeTo)
                .archived(false)
                .build();
        return discountService.getAll(sortBy, sortDirection, page, size, filter);
    }

    @GetMapping("/{discountId}")
    @ApiOperation("Get discount by ID")
    @UserAccess
    public DiscountDTO getDiscountById(@PathVariable(name = "discountId") @NotNull UUID id) {
        return discountService.getById(id);
    }

    @PostMapping
    @ApiOperation("Add new discount")
    @AdminAccess
    public DiscountDTO addDiscount(@RequestBody @Valid CreateDiscountDTO discountDTO) {
        return discountService.save(discountDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete discount")
    @AdminAccess
    public void deleteDiscount(@PathVariable(name = "id") @NotNull UUID id) {
        discountService.deleteById(id);
    }

    @GetMapping("/archived")
    @ApiOperation("Get all archived Discounts")
    @AdminAccess
    public List<DiscountDTO> getAllArchivedDiscounts(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                     @RequestParam(defaultValue = "12", required = false) Integer size,
                                                     @RequestParam(defaultValue = "id", required = false) String sortBy,
                                                     @RequestParam(defaultValue = "asc", required = false)
                                                             String sortDirection,
                                                     @RequestParam(required = false) List<UUID> vendorId,
                                                     @RequestParam(required = false) List<UUID> categoryId,
                                                     @RequestParam(required = false) List<UUID> countryId,
                                                     @RequestParam(required = false) List<UUID> cityId,
                                                     @RequestParam(required = false) List<UUID> tagId,
                                                     @RequestParam(required = false)
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                             LocalDateTime endDateTimeFrom,
                                                     @RequestParam(required = false)
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                             LocalDateTime endDateTimeTo) {
        DiscountFilter filter = DiscountFilter.builder()
                .vendorIds(vendorId)
                .categoryIds(categoryId)
                .countryIds(countryId)
                .cityIds(cityId)
                .tagIds(tagId)
                .endDateFrom(endDateTimeFrom)
                .endDateTo(endDateTimeTo)
                .archived(true)
                .build();
        return discountService.getAll(sortBy, sortDirection, page, size, filter);
    }

    @PutMapping("/archived/{id}/restore")
    @ApiOperation("Restore Discount by ID")
    @AdminAccess
    public DiscountDTO restoreDiscount(@PathVariable UUID id) {
        return discountService.restoreById(id);
    }

    @GetMapping("/search")
    @ApiOperation("Get Discounts by search text")
    @UserAccess
    public List<DiscountDTO> search(@RequestParam(defaultValue = "8", required = false) Integer size,
                                    @RequestParam String searchText) {
        return discountService.search(size, searchText);
    }
}
