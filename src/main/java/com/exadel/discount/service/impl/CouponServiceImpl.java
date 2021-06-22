package com.exadel.discount.service.impl;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.CouponMapper;
import com.exadel.discount.repository.CouponRepository;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.service.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final CouponMapper couponMapper;

    @Override
    public List<CouponDto> findAllCoupons() {
        log.debug("Getting list of all Coupons");

        List<Coupon> coupons = couponRepository.findAll();
        log.debug(" Successfully list of all Coupons is got");

        return couponMapper.toCouponDtoList(coupons);
    }

    @Override
    public CouponDto findCouponById(UUID id) {
        log.debug("Finding Coupon by ID");

        Coupon coupon = couponRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Coupon with id %s not found", id)));

        log.debug("Successfully Coupon is found by ID");
        return couponMapper.toCouponDto(coupon);
    }

    @Transactional
    @Override
    public CouponDto addCouponToUser(UUID userId, CouponDto couponDto) {
        log.debug("Finding of certain user by ID");

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", userId)));
        log.debug("Successfully certain user is found by ID");

        Coupon coupon = couponMapper.toCoupon(couponDto);
        log.debug("Saving new Coupon to certain user");

        user.addCoupon(coupon);
        coupon.setUser(user);
        couponRepository.save(coupon);
        log.debug("Successfully new Coupon is saved to certain user");

        return couponMapper.toCouponDto(coupon);
    }

    @Override
    public CouponDto findCouponByDate(LocalDateTime date) {
        log.debug("Finding coupon by date");

        Coupon coupon = couponRepository.findCouponByDate(date);
        log.debug("Successfully coupon is found by date");

        return couponMapper.toCouponDto(coupon);
    }

    @Transactional
    @Override
    public CouponDto editCouponDate(UUID id, LocalDateTime newDate) {
        log.debug("Getting Coupon by ID");

        Coupon couponUnderEdition = couponRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Coupon with id %s not found", id)));
        log.debug("Coupon by ID is successfully got");

        log.debug("Editing of Coupon date");

        couponUnderEdition.setDate(newDate);
        log.debug("Successfully Coupon date is edited");

        return couponMapper.toCouponDto(couponUnderEdition);
    }

    @Transactional
    @Override
    public void deleteCouponById(UUID id) {
        log.debug("Deleting Coupon by ID");

        Coupon coupon = couponRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Coupon with id %s not found", id)));

        coupon.getUser().removeCoupon(coupon);
        couponRepository.deleteById(id);
        log.debug("Successfully Coupon is deleted by ID");

    }

    @Override
    public List<CouponDto> getCouponsOfUser(UUID userId) {
        log.debug("Finding of certain user by ID");

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", userId)));
        log.debug("Successfully user is found by ID");

        log.debug("Getting list of user's Coupons");

        List<Coupon> coupons = user.getCoupons();
        log.debug("Successfully list of user's Coupons is got");

        return couponMapper.toCouponDtoList(coupons);
    }
}
