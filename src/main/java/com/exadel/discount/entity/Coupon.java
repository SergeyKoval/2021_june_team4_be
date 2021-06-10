package com.exadel.discount.entity;

import com.exadel.discount.dto.CouponDto;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "coupons")

public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "ordersIdSeq", sequenceName = "orders_id_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordersIdSeq")
    private UUID id;
    private int price;
    private String serialNumber;

    @ManyToOne
    private Person person;

    public Coupon() {
    }

    public Coupon(int price, String serialNumber, Person person) {
        this.price = price;
        this.serialNumber = serialNumber;
        this.person = person;
    }

    public static Coupon from(CouponDto couponDto) {
        Coupon coupon = new Coupon();
        coupon.setPrice(couponDto.getPrice());
        coupon.setSerialNumber(couponDto.getSerialNumber());
        return coupon;


    }
}
