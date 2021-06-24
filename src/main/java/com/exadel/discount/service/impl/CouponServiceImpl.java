package com.exadel.discount.service.impl;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.dto.coupon.CreateCouponDto;
import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.Discount;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.CouponMapper;
import com.exadel.discount.repository.CouponRepository;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.service.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final CouponMapper couponMapper;
    private final DiscountRepository discountRepository;

    @Override
    public List<CouponDto> findAllCoupons() {
        log.debug("Getting list of all Coupons");

        List<Coupon> coupons = couponRepository.findAll();
        log.debug("Successfully list of all Coupons is got");

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
    public CouponDto assignCouponToUser(CreateCouponDto createCouponDto) {
        log.debug("Finding of certain User and Discount by ID");

        User user = userRepository
                .findById(createCouponDto.getUserId())
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", createCouponDto.getUserId())));

        Discount discount = discountRepository
                .findById(createCouponDto.getDiscountId())
                .orElseThrow(() -> new NotFoundException(String.format("Discount with id %s not found", createCouponDto.getDiscountId())));

        log.debug("Successfully certain User and Discount are found by ID. Starting Coupon creation/saving.");

        Coupon coupon = new Coupon();
        coupon.setDate(LocalDateTime.now());
        coupon.setUser(user);
        coupon.setDiscount(discount);
        couponRepository.save(coupon);
        log.debug("Successfully new Coupon is saved");

        return couponMapper.toCouponDto(coupon);
    }

    @Override
    public CouponDto findCouponByDate(LocalDateTime date) {
        log.debug("Finding coupon by date");

        Coupon coupon = couponRepository.findCouponByDate(date);
        if(coupon==null) throw new NotFoundException(String.format("Coupon with date %s not found", date));
        log.debug("Successfully coupon is found by date");

        return couponMapper.toCouponDto(coupon);
    }

    @Override
    public List<CouponDto> findCouponsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        log.debug("Finding coupon by date scope");
        List<CouponDto> CouponDtoList = couponMapper.toCouponDtoList(couponRepository.findAll()
                .stream()
                .filter(s -> s.getDate().isAfter(startDate))
                .filter(s -> s.getDate().isBefore(endDate))
                .collect(Collectors.toList()));
        log.debug("Successfully coupon is found by date scope");

        return CouponDtoList;
    }

    @Override
    public List<CouponDto> getCouponsOfUser(UUID userId) {
        log.debug("Finding Coupons of certain user");

        List<CouponDto> CouponDtoList = couponMapper.toCouponDtoList(couponRepository.findAll()
                .stream()
                .filter(s -> s.getUser().getId().equals(userId))
                .collect(Collectors.toList()));

        log.debug("Successfully list of user's Coupons is got");

        return CouponDtoList;
    }
}
