package com.exadel.discount.dto;

import com.exadel.discount.entity.Coupon;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class CouponDto {
    private UUID id;
    private int price;
    private String SerialNumber;
    private PlainPersonDto plainPersonDto;

    public static CouponDto from(Coupon coupon) {
        CouponDto couponDto = new CouponDto();
        couponDto.setId(coupon.getId());
        couponDto.setPrice(coupon.getPrice());
        couponDto.setSerialNumber(coupon.getSerialNumber());
        if (Objects.nonNull(coupon.getPerson())) {
            couponDto.setPlainPersonDto(PlainPersonDto.from(coupon.getPerson()));
        }
        return couponDto;
    }
}
