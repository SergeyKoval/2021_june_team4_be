package com.exadel.discount.repository;

import com.exadel.discount.model.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID>, QueryFactoryCouponRepository {

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country",
            "discount.discountImages"})
    Optional<Coupon> findById(UUID userId);

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country",
            "discount.discountImages"})
    Page<Coupon> findAll(Pageable paging);

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country",
            "discount.discountImages"})
    List<Coupon> findAllByIdIn(Iterable<UUID> ids, Sort sort);

    boolean existsByDiscountIdAndUserEmail(UUID discountId, String userEmail);
}
