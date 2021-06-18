package com.exadel.discount.mapper;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.entity.Coupon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    Coupon toCoupon(CouponDto couponDto);

    CouponDto toCouponDto(Coupon coupon);

    List<CouponDto> toCouponDtoList(List<Coupon> coupons);

    List<Coupon> toCouponList(List<CouponDto> couponDtos);

}
