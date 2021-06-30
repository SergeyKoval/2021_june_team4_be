package com.exadel.discount.repository;

import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID> {
    Optional<Coupon> findCouponByDate(LocalDateTime date);
    List<Coupon> findByUser(UUID id);

    @Query(value = "select c from Coupon c where c.date BETWEEN :startDate and :endDate", countQuery = "select count(c) from Coupon c where c.date BETWEEN :startDate and :endDate")
    Page<Coupon> dateSearch(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);
}
