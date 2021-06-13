package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.CouponNotFoundAtSuchDateException;
import com.exadel.discount.exception.CouponNotFoundException;
import com.exadel.discount.exception.UserNotFoundException;
import com.exadel.discount.repository.CouponRepository;
import com.exadel.discount.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository, UserRepository userRepository) {
        this.couponRepository = couponRepository;
        this.userRepository = userRepository;
    }
    @Transactional
        public Coupon addCoupon(Coupon coupon, UUID userId) {
        try {
            User user = userRepository.findUserById(userId);
            coupon.setUser(user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        couponRepository.save(coupon);
        return coupon;
    }
    @Transactional
    public Coupon switchCouponToAnotherUser(UUID couponId, UUID anotherUserId) {
        Coupon coupon = null;
        try {
            User user = userRepository.findUserById(anotherUserId);
            coupon = couponRepository.findCouponById(anotherUserId);
            user.addCoupon(coupon);
            coupon.setUser(user);
        } catch (UserNotFoundException | CouponNotFoundException e) {
            e.printStackTrace();
        }
        return coupon;
    }

    @Transactional
    public Coupon deleteCoupon(UUID id) {
            Coupon deletedCoupon = findCouponById(id);
            couponRepository.delete(deletedCoupon);
//            User user = deletedCoupon.getUser();
//            user.removeCoupon(deletedCoupon);
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
        Coupon coupon = null;
        try {
            coupon = couponRepository.findCouponByDate(date);

        } catch (CouponNotFoundAtSuchDateException e) {
            e.printStackTrace();
        }
        return coupon;
    }

    @Transactional
    @Override
    public Coupon editCoupon(UUID id, Coupon coupon) {
        Coupon couponUnderEdition = findCouponById(id);
        couponUnderEdition.setDate(coupon.getDate());
        return coupon;
    }

}
