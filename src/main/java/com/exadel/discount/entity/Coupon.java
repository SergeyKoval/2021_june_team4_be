package com.exadel.discount.entity;

import com.exadel.discount.dto.coupon.BaseCouponDto;
import com.exadel.discount.dto.coupon.CouponDto;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "coupons")

public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Coupon() {
    }

    public static Coupon from(CouponDto couponDto) {
        Coupon coupon = new Coupon();
        coupon.setDate(couponDto.getDate());
        return coupon;
    }

    public static Coupon from(BaseCouponDto baseCouponDto) {
        Coupon coupon = new Coupon();
        coupon.setDate(baseCouponDto.getDate());
        return coupon;
    }


}
