package com.exadel.discount.controller;

import com.exadel.discount.model.dto.coupon.CouponDTO;
import com.exadel.discount.model.dto.coupon.CouponFilter;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.security.annotation.UserAccess;
import com.exadel.discount.service.CouponService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    @ApiOperation("Get page-list of all coupons with filtering/sorting")
    @AdminAccess
    public List<CouponDTO> getAllCoupons(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                         @RequestParam(required = false, defaultValue = "10") int pageSize,
                                         @RequestParam(required = false, defaultValue = "") String sortDirection,
                                         @RequestParam(required = false, defaultValue = "id") String sortField,
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
                                                 LocalDateTime endDateTimeTo,
                                         @RequestParam(value = "startDate", required = false)
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final
                                         LocalDateTime creationTimeFrom,
                                         @RequestParam(value = "endDate", required = false)
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final
                                         LocalDateTime creationTimeTo,
                                         @RequestParam(value = "userId", required = false) UUID userId) {
        CouponFilter filter = CouponFilter.builder()
                .vendorIds(vendorId)
                .categoryIds(categoryId)
                .countryIds(countryId)
                .cityIds(cityId)
                .tagIds(tagId)
                .userId(userId)
                .endDateFrom(endDateTimeFrom)
                .endDateTo(endDateTimeTo)
                .creationTimeFrom(creationTimeFrom)
                .creationTimeTo(creationTimeTo)
                .build();
        return couponService.getAll(pageNumber, pageSize, sortDirection, sortField, filter);
    }

    @GetMapping("/search")
    @ApiOperation("Get Coupons by search text")
    @UserAccess
    public List<CouponDTO> search(@RequestParam(defaultValue = "8", required = false) Integer size,
                                  @RequestParam String searchText) {
        return couponService.search(size, searchText);
    }

    @GetMapping("{id}")
    @ApiOperation("Get coupon by ID")
    @AdminAccess
    public CouponDTO getCouponById(@PathVariable @NotNull final UUID id) {
        return couponService.findCouponById(id);
    }

    @PostMapping
    @ApiOperation("Save new coupon to user")
    @UserAccess
    public CouponDTO addCoupon(@RequestParam @NotNull final UUID discountId) {
        return couponService.assignCouponToUser(discountId);
    }
}
