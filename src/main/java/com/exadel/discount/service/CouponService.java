package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface CouponService {
    Coupon findCouponById(UUID id);

    List<Coupon> deleteCoupon(UUID id);

    List<Coupon> findAllCoupons();

    Coupon addCouponToUser(UUID userId, Coupon coupon);

    Coupon findCouponByDate(LocalDateTime time);

    Coupon editCouponDate(UUID couponId, Coupon newDateCoupon);

    List<Coupon> getCouponsOfUser(UUID userId);
}
