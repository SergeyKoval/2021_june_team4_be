package com.exadel.discount.service;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.dto.coupon.CreateCouponDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface CouponService {
    CouponDto findCouponById(UUID id);

    List<CouponDto> findAllCoupons(int pageNumber, int pageSize, String sortDirection, String sortField, LocalDateTime startDate, LocalDateTime endDate);

    CouponDto assignCouponToUser(CreateCouponDto createCouponDto);

    CouponDto findCouponByDate(LocalDateTime time);

    List<CouponDto> getCouponsOfUser(int pageNumber, int pageSize, String sortDirection, String sortField, UUID userId);
}
