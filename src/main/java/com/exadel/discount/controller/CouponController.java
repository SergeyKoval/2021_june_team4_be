package com.exadel.discount.controller;

import com.exadel.discount.dto.coupon.BaseCouponDto;
import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.entity.Coupon;
import com.exadel.discount.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("{userId}")
    public ResponseEntity<CouponDto> addCoupon(@PathVariable final UUID userId,
                                               @RequestBody final BaseCouponDto baseCouponDto) {
        Coupon coupon = couponService.addCoupon(userId, Coupon.from(baseCouponDto));
        return new ResponseEntity<>(CouponDto.from(coupon), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CouponDto>> getAllCoupons() {
        List<Coupon> allCoupons = couponService.findAllCoupons();
        List<CouponDto> allCouponsDto = allCoupons.stream()
                .map(CouponDto::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allCouponsDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CouponDto> getCouponById(@PathVariable final UUID id) {
        Coupon coupon = couponService.findCouponById(id);
        return new ResponseEntity<>(CouponDto.from(coupon), HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<CouponDto> getCouponByDate(@PathVariable final Timestamp date) {
        Coupon coupon = couponService.findCouponByDate(date);
        return new ResponseEntity<>(CouponDto.from(coupon), HttpStatus.OK);
    }

    @DeleteMapping("{Id}")
    public ResponseEntity <List<CouponDto>> deleteCoupon(@PathVariable final UUID id) {
        List<Coupon> remaindedCoupons = couponService.deleteCoupon(id);
        List<CouponDto> remaindedCouponsDto = remaindedCoupons.stream()
                .map(CouponDto::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(remaindedCouponsDto, HttpStatus.OK);
    }

    @PutMapping("{Id}")
    public ResponseEntity<CouponDto> editCouponDate(@PathVariable final UUID Id,
                                                    @RequestBody final CouponDto newCouponDto) {
        Coupon editedCoupon = couponService.editCoupon(Id, Coupon.from(newCouponDto));
        return new ResponseEntity<>(CouponDto.from(editedCoupon), HttpStatus.OK);
    }

    @PutMapping("{couponId}/switchtouser/{anotherUserId}")
    public ResponseEntity<CouponDto> switchCouponToUser(@PathVariable final UUID couponId,
                                                        @PathVariable final UUID anotherUserId) {
        Coupon switchedCoupon = couponService.switchCouponToAnotherUser(couponId, anotherUserId);
        return new ResponseEntity<>(CouponDto.from(switchedCoupon), HttpStatus.OK);
    }
}

