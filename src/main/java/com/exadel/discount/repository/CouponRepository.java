package com.exadel.discount.repository;

import com.exadel.discount.entity.Coupon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.util.UUID;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, UUID> {
    Coupon findCouponByDate(Timestamp date);
}
