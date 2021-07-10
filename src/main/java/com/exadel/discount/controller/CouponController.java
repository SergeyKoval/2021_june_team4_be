package com.exadel.discount.controller;

import com.exadel.discount.dto.coupon.CouponDTO;
import com.exadel.discount.dto.coupon.CouponFilter;
import com.exadel.discount.dto.coupon.CreateCouponDTO;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.security.annotation.UserAccess;
import com.exadel.discount.service.CouponService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/coupons")
public class CouponController {
    private final CouponService couponService;

    @GetMapping
    @ApiOperation("Get page-list of all coupons with filtering/sorting")
    @AdminAccess
    public List<CouponDTO> getAllCoupons(@RequestParam(name = "pageNumber", defaultValue = "1", required = false)
                                                     int pageNumber,
                                         @RequestParam(name = "pageSize", defaultValue = "10", required = false)
                                                    int pageSize,
                                         @RequestParam(value = "sortDirection", defaultValue = "", required = false)
                                                     String sortDirection,
                                         @RequestParam(value = "sortField", defaultValue = "id", required = false)
                                                     String sortField,
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
                                         @RequestParam(value = "startDate", required = false)
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                             final LocalDateTime startDate,
                                         @RequestParam(value = "endDate", required = false)
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                             final LocalDateTime endDate,
                                         @RequestParam(value = "userId", required = false) UUID userId) {
        CouponFilter filter = CouponFilter.builder()
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
                .startDate(startDate)
                .endDate(endDate)
                .build();
        return couponService.findAllCoupons(pageNumber, pageSize, sortDirection, sortField, filter);
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
    public CouponDTO addCoupon(@RequestBody @NotNull final CreateCouponDTO createCouponDTO) {
        return couponService.assignCouponToUser(createCouponDTO);
    }

    //Time example : 2021-06-15T11:58:11
    @GetMapping("/date")
    @ApiOperation("Get coupon by certain date")

    public CouponDTO getCouponByDate(@RequestParam("date")
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                         @NotNull final LocalDateTime date) {
        return couponService.findCouponByDate(date);
    }
}
