package com.exadel.discount.service;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.CouponNotFoundAtSuchDateException;
import com.exadel.discount.exception.CouponNotFoundException;
import com.exadel.discount.exception.UserNotFoundException;
import com.exadel.discount.mapper.CouponMapper;
import com.exadel.discount.repository.CouponRepository;
import com.exadel.discount.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final CouponMapper couponMapper;

    @Transactional
    @Override
    public CouponDto addCouponToUser(UUID userId, CouponDto couponDto) {
        User user = userRepository.findById(userId);
        Coupon coupon = couponMapper.toCoupon(couponDto);
        user.addCoupon(coupon);
        coupon.setUser(user);
        couponRepository.save(coupon);
        return couponMapper.toCouponDto(coupon);
    }

    @Override
    public CouponDto findCouponById(UUID id) {
        return couponRepository
                .findById(id)
                .map(couponMapper::toCouponDto);
    }

    @Override
    public List<CouponDto> findAllCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        return couponMapper.toCouponDtoList(coupons);
    }

    @Override
    public CouponDto findCouponByDate(LocalDateTime date) {
        Coupon coupon = couponRepository.findCouponByDate(date);
        coupon.setUser(null);
        return couponMapper.toCouponDto(coupon);
    }

    @Transactional
    @Override
    public CouponDto editCouponDate(UUID id, LocalDateTime newDate) {
        Coupon couponUnderEdition = couponRepository.findById(id);
        couponUnderEdition.setDate(newDate);
        return couponMapper.toCouponDto(couponUnderEdition);
    }

    @Transactional
    @Override
    public void deleteCoupon(UUID id) {
        Coupon coupon = couponRepository.findById(id);
        coupon.getUser().removeCoupon(coupon);
        couponRepository.deleteById(id);
    }

    @Override
    public List<CouponDto> getCouponsOfUser(UUID userId) {
        User user  = userRepository.findById(userId);
        List<Coupon> coupons = user.getCoupons();
        return couponMapper.toCouponDtoList(coupons);
    }
}