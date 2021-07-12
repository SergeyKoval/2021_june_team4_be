package com.exadel.discount.mapper;

import com.exadel.discount.dto.coupon.CouponDTO;
import com.exadel.discount.entity.Coupon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DiscountMapper.class})
public interface CouponMapper {

    CouponDTO toCouponDTO (Coupon coupon);

    List<CouponDTO> toCouponDTOList (List<Coupon> coupons);
}
