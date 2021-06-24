package com.exadel.discount.controller;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.dto.coupon.CreateCouponDto;
import com.exadel.discount.service.CouponService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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

    @PostMapping
    @ApiOperation("Save new coupon")
    public CouponDto addCoupon(@RequestBody final CreateCouponDto createCouponDto) {
        return couponService.assignCouponToUser(createCouponDto);
    }

    @GetMapping("/ofuser")
    @ApiOperation("Get coupons of certain user")
    public List<CouponDto> getCouponsOfUser(@RequestParam("userId") final UUID userId) {
        return couponService.getCouponsOfUser(userId);
    }

    //Time example : 2021-06-15T11:58:11
    @GetMapping("/date")
    @ApiOperation("Get coupon by date")
    public CouponDto getCouponByDate(@RequestParam("date")
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime date) {
        return couponService.findCouponByDate(date);
    }

    @GetMapping("/date/between")
    @ApiOperation("Get coupon by date scope")
    public List<CouponDto> getCouponAtDateScope(@RequestParam("startdate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime startDate,
                                                @RequestParam("enddate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime endDate) {
        return couponService.findCouponsBetweenDates(startDate, endDate);
    }
}
