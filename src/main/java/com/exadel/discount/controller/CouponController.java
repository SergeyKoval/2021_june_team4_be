package com.exadel.discount.controller;

import com.exadel.discount.dto.CouponDto;
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

    @PostMapping
    public ResponseEntity<CouponDto> addCoupon(@RequestBody CouponDto couponDto) {
        Coupon coupon = couponService.addCoupon(Coupon.from(couponDto));
        return new ResponseEntity<>(CouponDto.from(coupon), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CouponDto>> getAllCoupons() {
        List<Coupon> allCoupons = couponService.findAllCoupons();
        List<CouponDto> allCouponsDto = allCoupons.stream().map(CouponDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(allCouponsDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CouponDto> getCouponById(@PathVariable final UUID id) {
        Coupon coupon = couponService.findCouponById(id);
        return new ResponseEntity<>(CouponDto.from(coupon), HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<CouponDto> getCouponByDate(@PathVariable final Timestamp date) {
        Coupon coupon = couponService.findCouponByDate(date);
        return new ResponseEntity<>(CouponDto.from(coupon), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<CouponDto> deleteOrder(@PathVariable final UUID id) {
        Coupon coupon = couponService.deleteCoupon(id);
        return new ResponseEntity<>(CouponDto.from(coupon), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CouponDto> editOrder(@PathVariable final UUID id,
                                               @RequestBody final CouponDto couponDto) {
        Coupon editedCoupon = couponService.editCoupon(id, Coupon.from(couponDto));
        return new ResponseEntity<>(CouponDto.from(editedCoupon), HttpStatus.OK);
    }
}
