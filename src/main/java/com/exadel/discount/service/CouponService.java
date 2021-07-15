package com.exadel.discount.service;

import com.exadel.discount.model.dto.coupon.CouponDTO;
import com.exadel.discount.model.dto.coupon.CouponFilter;

import java.util.List;
import java.util.UUID;

public interface CouponService {
    CouponDTO findCouponById(UUID id);

    List<CouponDTO> getAll(int pageNumber, int pageSize, String sortDirection, String sortField, CouponFilter filter);

    CouponDTO assignCouponToUser(UUID discountId);

    List<CouponDTO> search(Integer size, String searchText);
}
