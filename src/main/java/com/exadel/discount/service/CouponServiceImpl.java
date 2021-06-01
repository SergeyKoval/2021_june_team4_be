package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.CouponNotFoundAtSuchDateException;
import com.exadel.discount.exception.CouponNotFoundException;
import com.exadel.discount.repository.CouponRepository;
import com.exadel.discount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository, UserRepository userRepository) {
        this.couponRepository = couponRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Coupon addCouponToUser(UUID userId, Coupon coupon) {
        User user = userRepository.findUserById(userId);
        user.addCoupon(coupon);
        coupon.setUser(user);
        couponRepository.save(coupon);
        return coupon;
    }

    @Override
    public Coupon findCouponById(UUID id) {
        return couponRepository
                .findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id));
    }

    @Override
    public List<Coupon> findAllCoupons() {
        return StreamSupport
                .stream(couponRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Coupon findCouponByDate(LocalDateTime date) {
        Coupon coupon = couponRepository.findCouponByDate(date);
        if (coupon == null) throw new CouponNotFoundAtSuchDateException(date);
        return coupon;
    }

    @Transactional
    @Override
    public Coupon editCouponDate(UUID id, Coupon newDateCoupon) {
        Coupon couponUnderEdition = findCouponById(id);
        couponUnderEdition.setDate(newDateCoupon.getDate());
        return couponUnderEdition;
    }

    @Transactional
    @Override
    public List<Coupon> deleteCoupon(UUID id) {
        Coupon coupon = findCouponById(id);
        coupon.getUser().removeCoupon(coupon);
        couponRepository.delete(coupon);
        return findAllCoupons();
    }

    @Override
    public List<Coupon> getCouponsOfUser(UUID userId) {
        return userRepository.findUserById(userId).getCoupons();

    }
}
