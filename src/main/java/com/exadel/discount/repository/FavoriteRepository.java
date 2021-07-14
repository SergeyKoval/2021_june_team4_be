package com.exadel.discount.repository;

import com.exadel.discount.model.entity.Favorite;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, UUID>, QuerydslPredicateExecutor<Favorite>,
        QueryFactoryFavoriteRepository {
    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
    Page<Favorite> findAll(Predicate predicate, Pageable paging);

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
    Page<Favorite> findAll(Pageable paging);

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
    Optional<Favorite> findById(UUID id);

    @EntityGraph(attributePaths = {"discount", "discount.category", "discount.vendorLocations", "discount.tags",
            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
    List<Favorite> findAllByIdIn(Iterable<UUID> ids, Sort sort);

//    @EntityGraph(attributePaths = {"user", "user.city.country", "user.city", "discount", "discount.category"
//    , "discount.vendorLocations", "discount.tags",
//            "discount.vendor", "discount.vendorLocations.city", "discount.vendorLocations.city.country"})
//    boolean existsFavoriteByDiscountIdAndAndUserEmail(@Param("discountId") UUID discountId,
//                                                      @Param("userEmail") String userEmail);
    @Modifying
    void deleteFavoriteByDiscountIdAndUserEmail(@Param("discountId") UUID discountId,
                                                   @Param("userEmail") String userEmail);


//    @EntityGraph(attributePaths = {"user", "discount"})
//    @Query("SELECT f FROM Favorite f WHERE f.discount.id =:discountId AND f.user.email=:userEmail")
//    Optional<Favorite> existsFavoriteByDiscountIdAndAndUserEmail(@Param("discountId") UUID discountId,
//                                                      @Param("userEmail") String userEmail);

    @EntityGraph(attributePaths = {"user", "discount"})
 //   @Query("SELECT f FROM Favorite f WHERE f.discount.id =:discountId AND f.user.email=:userEmail")
    Optional<Favorite> findByDiscountIdAndAndUserEmail(@Param("discountId") UUID discountId,
                                                                 @Param("userEmail") String userEmail);

}
