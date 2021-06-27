package com.exadel.discount.service;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.dto.coupon.CreateCouponDto;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface CouponService {
    CouponDto findCouponById(UUID id);

    List<CouponDto> findAllCoupons(Sort sort);

    CouponDto assignCouponToUser(CreateCouponDto createCouponDto);

    CouponDto findCouponByDate(LocalDateTime time);

    List<CouponDto> findCouponsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<CouponDto> getCouponsOfUser(UUID userId);
}
