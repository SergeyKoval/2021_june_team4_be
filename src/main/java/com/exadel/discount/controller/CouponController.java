package com.exadel.discount.controller;

import com.exadel.discount.dto.coupon.BaseCouponDto;
import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.entity.Coupon;
import com.exadel.discount.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    //Write in Body of request:      "date":"2021-06-15T11:58:11"
    public ResponseEntity<CouponDto> addCoupon(@PathVariable final UUID userId,
                                               @RequestBody final BaseCouponDto baseCouponDto) {
        Coupon coupon = couponService.addCouponToUser(userId, Coupon.from(baseCouponDto));
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

    //Write in link instead {date} :      "date":"2021-06-15T11:58:11"
    @GetMapping("/date/{date}")
    public ResponseEntity<CouponDto> getCouponByDate(@PathVariable
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime date) {
        Coupon coupon = couponService.findCouponByDate(date);
        return new ResponseEntity<>(CouponDto.from(coupon), HttpStatus.OK);
    }

    @GetMapping("ofuser/{userId}")
    public ResponseEntity<List<CouponDto>> getCouponsOfUser(@PathVariable final UUID userId) {
        List<Coupon> couponsOfUser = couponService.getCouponsOfUser(userId);
        List<CouponDto> couponsOfUserDto = couponsOfUser.stream()
                .map(CouponDto::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(couponsOfUserDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<CouponDto>> deleteCoupon(@PathVariable final UUID id) {
        List<Coupon> remaindedCoupons = couponService.deleteCoupon(id);
        List<CouponDto> remaindedCouponsDto = remaindedCoupons.stream()
                .map(CouponDto::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(remaindedCouponsDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CouponDto> editCouponDate(@PathVariable final UUID id,
                                                    @RequestBody final BaseCouponDto newDateCoupon) {
        Coupon editedCoupon = couponService.editCouponDate(id, Coupon.from(newDateCoupon));
        return new ResponseEntity<>(CouponDto.from(editedCoupon), HttpStatus.OK);
    }
}

