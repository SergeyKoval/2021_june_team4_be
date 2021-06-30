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
    Page<Coupon> findByUserId(UUID userId, Pageable paging);

//    @Query(value = "SELECT c FROM Coupon c WHERE c.date BETWEEN :startDate AND :endDate", countQuery = "SELECT count(c) FROM Coupon c WHERE c.date BETWEEN :startDate AND :endDate")
//    Page<Coupon> dateSearch(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    Page<Coupon> findCouponsByDateBetween( LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
