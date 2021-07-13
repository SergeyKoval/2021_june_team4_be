package com.exadel.discount.service;

import com.exadel.discount.model.dto.coupon.CouponDTO;
import com.exadel.discount.model.dto.coupon.CouponFilter;
import com.exadel.discount.model.dto.coupon.CreateCouponDTO;

import java.util.List;
import java.util.UUID;


public interface CouponService {
    CouponDTO findCouponById(UUID id);

    List<CouponDTO> search(int pageNumber, int pageSize, String sortDirection, String sortField, CouponFilter filter);

    CouponDTO assignCouponToUser(CreateCouponDTO createCouponDTO);
}
