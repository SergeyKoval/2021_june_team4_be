package com.exadel.discount.service;

import com.exadel.discount.dto.coupon.CouponDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface CouponService {
    CouponDto findCouponById(UUID id);

    List<CouponDto> findAllCoupons();

    CouponDto assignCouponToUser(UUID userId, UUID discountId);

    CouponDto findCouponByDate(LocalDateTime time);

    List<CouponDto> findCouponsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<CouponDto> getCouponsOfUser (UUID userId);
}
