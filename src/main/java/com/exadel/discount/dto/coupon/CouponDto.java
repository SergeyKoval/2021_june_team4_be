package com.exadel.discount.dto.coupon;

import com.exadel.discount.dto.user.BaseUserDto;
import com.exadel.discount.entity.Coupon;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CouponDto {
    private UUID id;
    private LocalDateTime date;
    private BaseUserDto baseUserDto;


    public static CouponDto from(Coupon coupon) {
        CouponDto couponDto = new CouponDto();
        couponDto.setId(coupon.getId());
        couponDto.setDate(coupon.getDate());
        couponDto.setBaseUserDto(BaseUserDto.from(coupon.getUser()));
        return couponDto;
    }
}
