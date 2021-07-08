package com.exadel.discount.mapper;

import com.exadel.discount.dto.coupon.CouponDTO;
import com.exadel.discount.entity.Coupon;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CouponMapper{
    @Autowired
    DiscountMapper discountMapper;
    public CouponDTO toCouponDTO(Coupon coupon){
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setDate(coupon.getDate());
        couponDTO.setId(coupon.getId());
        couponDTO.setDiscountDto(discountMapper.getDTO(coupon.getDiscount()));
        return couponDTO;
    }

    public abstract List<CouponDTO> toCouponDTOList(List<Coupon> coupons);
}
