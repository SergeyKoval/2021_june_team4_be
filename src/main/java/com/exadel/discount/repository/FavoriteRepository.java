package com.exadel.discount.repository;

import com.exadel.discount.model.entity.Favorite;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, UUID>, QueryFactoryFavoriteRepository {

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country",
            "discount.discountImages"})
    Optional<Favorite> findById(UUID id);

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country",
            "discount.discountImages"})
    List<Favorite> findAllByIdIn(Iterable<UUID> ids, Sort sort);

    void deleteFavoriteByDiscountIdAndUserEmail(UUID discountId, String userEmail);

    boolean existsByDiscountIdAndUserEmail(UUID discountId, String userEmail);

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country",
            "discount.discountImages"})
    Optional<Favorite> findByDiscountIdAndUserEmail(UUID discountId, String userEmail);
}
