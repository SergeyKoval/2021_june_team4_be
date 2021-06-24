package com.exadel.discount.repository;

import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiscountRepository extends JpaRepository<Discount, UUID> {
}
