package com.exadel.discount.controller;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.service.CouponService;
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

    @PostMapping("{userId}")
    public CouponDto addCoupon(@Validated(com.exadel.discount.dto.validation.Create.class)@PathVariable final UUID userId,
                               @RequestBody CouponDto couponDto) {
        return couponService.addCouponToUser(userId, couponDto);
    }

    @GetMapping
    public List<CouponDto> getAllCoupons() {
        return couponService.findAllCoupons();
    }

    @GetMapping("{id}")
    public CouponDto getCouponById(@PathVariable final UUID id) {
        return couponService.findCouponById(id);
    }


    @GetMapping("ofuser/{userId}")
    public List<CouponDto> getCouponsOfUser(@PathVariable final UUID userId) {
        return couponService.getCouponsOfUser(userId);
    }

    @DeleteMapping("{id}")
    public void deleteCoupon(@PathVariable final UUID id) {
        couponService.deleteCoupon(id);
    }

    //Time example : 2021-06-15T11:58:11
    @PutMapping("{id/newDate}")
    public CouponDto editCouponDate(@PathVariable final UUID id,
                                    @PathVariable
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime newDate) {
        return couponService.editCouponDate(id, newDate);
    }

    @GetMapping("date/{date}")
    public CouponDto getCouponByDate(@PathVariable
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime date) {
        return couponService.findCouponByDate(date);
    }
}

