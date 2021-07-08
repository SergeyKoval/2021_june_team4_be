package com.exadel.discount.service;

import com.exadel.discount.dto.coupon.CouponDTO;
import com.exadel.discount.dto.coupon.CouponFilter;
import com.exadel.discount.dto.coupon.CreateCouponDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface CouponService {
    CouponDTO findCouponById(UUID id);

    List<CouponDTO> findAllCoupons(int pageNumber, int pageSize, String sortDirection, String sortField, CouponFilter filter);

    CouponDTO findCouponByDate(LocalDateTime time);

    CouponDTO assignCouponToUser(CreateCouponDTO createCouponDTO);
}
