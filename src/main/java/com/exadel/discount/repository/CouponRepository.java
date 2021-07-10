package com.exadel.discount.repository;

import com.exadel.discount.entity.Coupon;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID>, QuerydslPredicateExecutor<Coupon> {
    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
    Optional<Coupon> findById(UUID userId);

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
    Optional<Coupon> findCouponByDate(LocalDateTime date);

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
    Page<Coupon> findAll(Predicate predicate, Pageable paging);
}
