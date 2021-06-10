package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface CouponService {
    Coupon findCouponById(UUID id);

    Coupon deleteCoupon(UUID id);

    List<Coupon> findAllCoupons();

    Coupon addCoupon(Coupon coupon);

    List<Coupon> findCouponByPrice(int price);
    List<Coupon> findCouponBySerialNumber(String serialNumber);
    Coupon editCoupon(UUID id, Coupon coupon);

}
