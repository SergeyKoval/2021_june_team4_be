package com.exadel.discount.service;

import com.exadel.discount.dto.coupon.CouponDTO;
import com.exadel.discount.dto.coupon.CreateCouponDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface CouponService {
    CouponDTO findCouponById(UUID id);

    List<CouponDTO> findAllCoupons(int pageNumber, int pageSize, String sortDirection, String sortField, LocalDateTime startDate, LocalDateTime endDate);

    CouponDTO assignCouponToUser(CreateCouponDTO createCouponDTO);

    CouponDTO findCouponByDate(LocalDateTime time);

    List<CouponDTO> getCouponsOfUser(int pageNumber, int pageSize, String sortDirection, String sortField, UUID userId);
}
