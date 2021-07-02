package com.exadel.discount.repository;

import com.exadel.discount.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID> {
    Optional<Coupon> findCouponByDate(LocalDateTime date);
    Page<Coupon> findByUserId(UUID userId, Pageable paging);

    Page<Coupon> findCouponsByDateBetween( LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
