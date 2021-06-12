package com.exadel.discount.entity;

import com.exadel.discount.dto.CouponDto;
import lombok.Data;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "coupons")

public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Timestamp date;
   // private UUID serialNumber;

    @ManyToOne
    private User user;

    public Coupon() {
    }
    public static Coupon from(CouponDto couponDto) {
        Coupon coupon = new Coupon();
        coupon.setDate(couponDto.getDate());
     //   coupon.setSerialNumber(couponDto.getSerialNumber());
        return coupon;


    }
}
