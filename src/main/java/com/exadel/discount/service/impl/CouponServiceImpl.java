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
import com.exadel.discount.service.SortPageMaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final CouponMapper couponMapper;
    private final DiscountRepository discountRepository;

    @Override
    public List<CouponDto> findAllCoupons(int pageNumber, int pageSize, String sortDirection, String sortField, LocalDateTime startDate, LocalDateTime endDate) {
       Pageable paging = SortPageMaker.makePageable(pageNumber, pageSize, sortDirection, sortField);
        log.debug("Getting sorted page-list of all Coupons");

        List<Coupon> filteredCouponList = couponRepository.findCouponsByDateBetween(startDate, endDate, paging).toList();
        log.debug("Successfully sorted page-list of all Coupons is got");

        if (filteredCouponList.isEmpty()) {
            throw new NotFoundException(String.format("No Coupons %s are found", filteredCouponList));
        }
        return couponMapper.toCouponDtoList(filteredCouponList);
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

    public CouponDto findCouponByDate(LocalDateTime date) {
        log.debug("Finding coupon by date");

        Optional<Coupon> coupon = couponRepository.findCouponByDate(date);
        Coupon foundedCoupon;
        if (coupon.isPresent()) foundedCoupon = coupon.get();
        else throw new NotFoundException(String.format("Coupon with date %s not found", date));
        log.debug("Successfully coupon is found by date");

        return couponMapper.toCouponDto(foundedCoupon);
    }

    @Override
    public List<CouponDto> getCouponsOfUser(int pageNumber, int pageSize, String sortDirection, String sortField, UUID userId) {
        Pageable paging = SortPageMaker.makePageable(pageNumber, pageSize, sortDirection, sortField);
        log.debug("Getting sorted page-list Coupons of certain user");
        List<Coupon> CouponList = couponRepository.findByUserId(userId, paging).toList();
        if (CouponList.isEmpty())
            throw new NotFoundException(String.format("No Coupons are found at user with Id %s ", userId));
        log.debug("Successfully sorted page-list of user's Coupons is got");

        return couponMapper.toCouponDtoList(CouponList);
    }
}
