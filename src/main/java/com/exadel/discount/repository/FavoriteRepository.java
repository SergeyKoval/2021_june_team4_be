package com.exadel.discount.repository;

import com.exadel.discount.entity.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
    Page<Favorite> findByUserId(UUID userId, Pageable paging);

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
    Page<Favorite> findAll(Pageable paging);

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
    List<Favorite> findAll();

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
    Optional<Favorite> findById(UUID id);


}
