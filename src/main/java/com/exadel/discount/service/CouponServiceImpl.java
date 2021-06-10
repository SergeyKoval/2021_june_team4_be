package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;
import com.exadel.discount.exception.CouponNotFoundAtPriceException;
import com.exadel.discount.exception.CouponNotFoundAtSerialNumberException;
import com.exadel.discount.exception.CuoponNotFoundException;
import com.exadel.discount.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        } catch (CuoponNotFoundException e) {
            e.printStackTrace();
        }
        couponRepository.delete(deletedCoupon);
        return deletedCoupon;
    }

    @Override
    public Coupon findCouponById(UUID id) {
        return couponRepository.findById(id).orElseThrow(() -> new CuoponNotFoundException(id));
    }

    @Override
    public List<Coupon> findAllCoupons() {
        return StreamSupport
                .stream(couponRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Coupon> findCouponByPrice(int price) {
        List<Coupon> coupons = StreamSupport
                .stream(couponRepository.findByPrice(price).spliterator(), false)
                .collect(Collectors.toList());
        if (coupons.isEmpty()) throw new CouponNotFoundAtPriceException(price);
        return coupons;
    }

    @Override
    public List<Coupon> findCouponBySerialNumber(String serialNumber) {
        List<Coupon> coupons = StreamSupport
                .stream(couponRepository.findBySerialNumber(serialNumber)
                        .spliterator(), false)
                .collect(Collectors.toList());
        if (coupons.isEmpty()) throw new CouponNotFoundAtSerialNumberException(serialNumber);
        return coupons;
    }

    @Transactional
    @Override
    public Coupon editCoupon(UUID id, Coupon coupon) {
        Coupon couponUnderEdition = findCouponById(id);
        couponUnderEdition.setPrice(coupon.getPrice());
        couponUnderEdition.setSerialNumber(coupon.getSerialNumber());
        return coupon;
    }

}
