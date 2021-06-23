package com.exadel.discount.controller;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.service.CouponService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    @ApiOperation("Save new coupon")
    public CouponDto addCoupon(@RequestParam("userId") final UUID userId,
                               @RequestParam("discountId") final UUID discountId) {
        return couponService.assignCouponToUser(userId, discountId);
    }

    @GetMapping("/ofuser")
    @ApiOperation("Get coupons of certain user")
    public List<CouponDto> getCouponsOfUser(@RequestParam("userId") final UUID userId) {
        return couponService.getCouponsOfUser(userId);
    }

    //Time example : 2021-06-15T11:58:11
    @PutMapping
    @ApiOperation("Edit coupon date")
    public CouponDto editCouponDate(@RequestParam("couponId") final UUID id,
                                    @RequestParam("newDate")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime newDate) {
        return couponService.editCouponDate(id, newDate);
    }

    @GetMapping("/date")
    @ApiOperation("Get coupon by date")
    public CouponDto getCouponByDate(@RequestParam("date")
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime date) {
        return couponService.findCouponByDate(date);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete coupon by ID")
    public void deleteCouponById(@PathVariable final UUID id) {
        couponService.deleteCouponById(id);
    }

}
