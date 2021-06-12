package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;
import com.exadel.discount.exception.CouponNotFoundException;
import com.exadel.discount.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;

    @Autowired
    CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon addCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Coupon deleteCoupon(UUID id) {
        Coupon deletedCoupon = null;
        try {
            deletedCoupon = findCouponById(id);
        } catch (CouponNotFoundException e) {
            e.printStackTrace();
        }
        couponRepository.delete(deletedCoupon);
        return deletedCoupon;
    }

    @Override
    public Coupon findCouponById(UUID id) {
        return couponRepository.findById(id).orElseThrow(() -> new CouponNotFoundException(id));
    }

    @Override
    public List<Coupon> findAllCoupons() {
        return StreamSupport
                .stream(couponRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Coupon findCouponByDate(Timestamp date) {
        return couponRepository.findCouponByDate(date);
    }

    @Transactional
    @Override
    public Coupon editCoupon(UUID id, Coupon coupon) {
        Coupon couponUnderEdition = findCouponById(id);
        couponUnderEdition.setDate(coupon.getDate());
        return coupon;
    }

}
