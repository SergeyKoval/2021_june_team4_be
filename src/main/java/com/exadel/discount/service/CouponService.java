package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.security.Timestamp;
import java.util.List;
import java.util.UUID;

@Component
public interface CouponService {
    Coupon findCouponById(UUID id);

    Coupon deleteCoupon(UUID id);

    List<Coupon> findAllCoupons();

    Coupon addCoupon(Coupon coupon);

    Coupon findCouponByDate(Timestamp time);
    //List<Coupon> findCouponBySerialNumber(String serialNumber);
    Coupon editCoupon(UUID id, Coupon coupon);

}
