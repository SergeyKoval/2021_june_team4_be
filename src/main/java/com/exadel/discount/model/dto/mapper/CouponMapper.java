package com.exadel.discount.model.dto.mapper;

import com.exadel.discount.model.dto.coupon.CouponDTO;
import com.exadel.discount.model.entity.Coupon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DiscountMapper.class})
public interface CouponMapper {

    CouponDTO toCouponDTO (Coupon coupon);

    List<CouponDTO> toCouponDTOList (List<Coupon> coupons);
}
