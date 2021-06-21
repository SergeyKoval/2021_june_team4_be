package com.exadel.discount.controller;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.CouponService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {
    private final CouponService couponService;

    @GetMapping
    @ApiOperation("Get list of all coupons")
    public List<CouponDto> getAllCoupons() {
        return couponService.findAllCoupons();
    }

    @GetMapping("{id}")
    @ApiOperation("Get coupon by ID")
    public CouponDto getCouponById(@PathVariable final UUID id) {
        return couponService.findCouponById(id);
    }

    @PostMapping("{userId}")
    @ApiOperation("Save new coupon")
    public CouponDto addCoupon(@Validated(Create.class) @PathVariable final UUID userId,
                               @RequestBody CouponDto couponDto) {
        return couponService.addCouponToUser(userId, couponDto);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete coupon by ID")
    public void deleteCouponById(@PathVariable final UUID id) {
        couponService.deleteCouponById(id);
    }

    @GetMapping("ofuser/{userId}")
    @ApiOperation("Get coupons of certain user")
    public List<CouponDto> getCouponsOfUser(@PathVariable final UUID userId) {
        return couponService.getCouponsOfUser(userId);
    }

    //Time example : 2021-06-15T11:58:11
    @PutMapping("{id/newDate}")
    @ApiOperation("Edit coupon date")
    public CouponDto editCouponDate(@PathVariable final UUID id,
                                    @PathVariable
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime newDate) {
        return couponService.editCouponDate(id, newDate);
    }

    @GetMapping("date/{date}")
    @ApiOperation("Get coupon by date")
    public CouponDto getCouponByDate(@PathVariable
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime date) {
        return couponService.findCouponByDate(date);
    }
}
