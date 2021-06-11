package com.exadel.discount.dto;

import com.exadel.discount.entity.Coupon;
import lombok.Data;

import java.security.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Data
public class CouponDto {
    private UUID id;
    private Timestamp date;
    //private UUID SerialNumber;
    private PlainPersonDto plainPersonDto;

    public static CouponDto from(Coupon coupon) {
        CouponDto couponDto = new CouponDto();
        couponDto.setId(coupon.getId());
        couponDto.setDate(coupon.getDate());
        // couponDto.setSerialNumber(coupon.getSerialNumber());
        if (Objects.nonNull(coupon.getPerson())) {
            couponDto.setPlainPersonDto(PlainPersonDto.from(coupon.getPerson()));
        }
        return couponDto;
    }
}
