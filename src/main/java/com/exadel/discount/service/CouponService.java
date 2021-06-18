package com.exadel.discount.service;

import com.exadel.discount.dto.coupon.CouponDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface CouponService {
    CouponDto findCouponById(UUID id);

    void deleteCoupon(UUID id);

    List<CouponDto> findAllCoupons();

    CouponDto addCouponToUser(UUID userId, CouponDto couponDto);

    CouponDto findCouponByDate(LocalDateTime time);

    CouponDto editCouponDate(UUID couponId, LocalDateTime newDate);

    List<CouponDto> getCouponsOfUser(UUID userId);
}
