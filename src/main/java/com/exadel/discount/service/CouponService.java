package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


public interface CouponService {
    Coupon findCouponById(UUID id);

    List<Coupon> deleteCoupon(Coupon coupon);

    List<Coupon> findAllCoupons();

    Coupon addCoupon(Coupon coupon);

    Coupon findCouponByDate(Timestamp time);

    Coupon switchCouponToAnotherUser(UUID couponId, UUID anotherUserId);

    Coupon editCoupon(UUID id, Coupon coupon);

}
