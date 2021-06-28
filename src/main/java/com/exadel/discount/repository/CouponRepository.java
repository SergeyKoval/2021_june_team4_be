package com.exadel.discount.repository;

import com.exadel.discount.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID> {
    Optional<Coupon> findCouponByDate(LocalDateTime date);
    List<Coupon> findByUser(UUID id);
}
