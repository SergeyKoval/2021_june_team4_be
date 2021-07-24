package com.exadel.discount.repository;

import com.exadel.discount.model.dto.statistics.DiscountStatisticsDTO;
import com.exadel.discount.model.entity.Discount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, UUID>, QueryFactoryDiscountRepository {

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor",
            "vendorLocations.city", "vendorLocations.city.country", "discountImages"})
    List<Discount> findAll();

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor",
            "vendorLocations.city", "vendorLocations.city.country", "discountImages"})
    Optional<Discount> findById(UUID id);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor", "discountImages"})
    List<Discount> findAllByIdIn(Iterable<UUID> ids, Sort sort);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor",
            "vendorLocations.city", "vendorLocations.city.country", "favorites", "discountImages"})
    @Query("SELECT d FROM Discount d " +
            "LEFT JOIN d.favorites f " +
            "ON f.user.email = :userEmail " +
            "WHERE d.id IN :discountIds")
    List<Discount> findAllByIdInWithFavoritesByUser(@Param("discountIds") Iterable<UUID> ids, Sort sort,
                                                    @Param("userEmail") String userEmail);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor",
            "vendorLocations.city", "vendorLocations.city.country", "favorites", "discountImages"})
    @Query("SELECT d FROM Discount d " +
            "LEFT JOIN d.favorites f " +
            "ON f.user.email = :userEmail " +
            "WHERE d.id = :discountId AND d.archived = :archived")
    Optional<Discount> findByIdAndArchivedWithFavoritesByUser(
            @Param("discountId") UUID id, @Param("archived") boolean archived, @Param("userEmail") String userEmail);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor",
            "vendorLocations.city", "vendorLocations.city.country", "favorites", "discountImages"})
    @Query("SELECT d FROM Discount d " +
            "LEFT JOIN d.favorites f " +
            "ON f.user.email = :userEmail " +
            "LEFT JOIN d.vendorLocations l " +
            "ON l.city.id IN :cityIds OR l.city.country.id IN :countryIds " +
            "WHERE d.id IN :discountIds")
    List<Discount> findAllByIdInWithFavoritesByUserAndLocations(
            @Param("discountIds") Iterable<UUID> ids, Sort sort,
            @Param("userEmail") String userEmail, @Param("cityIds") Iterable<UUID> cityIds,
            @Param("countryIds") Iterable<UUID> countryIds);

    @Modifying
    @Query("UPDATE Discount d SET d.viewNumber = d.viewNumber + 1" +
            "WHERE d.id = :discountId")
    void increaseViewNumberById(@Param("discountId") UUID id);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor",
            "vendorLocations.city", "vendorLocations.city.country", "discountImages"})
    Optional<Discount> findByIdAndArchived(UUID id, boolean archived);

    @Modifying
    @Query("UPDATE Discount d SET d.archived=:archived WHERE d.id=:discountId")
    void setArchivedById(@Param("discountId") UUID id, @Param("archived") boolean archived);

    boolean existsByIdAndArchived(UUID id, boolean archived);

    boolean existsByIdAndArchivedAndVendorArchived(UUID id, boolean discountArchived, boolean vendorArchived);

    @Query("SELECT new com.exadel.discount.model.dto.statistics.DiscountStatisticsDTO " +
            "(d.id, d.name, d.viewNumber, COUNT(cou) AS numberOfGettingPromo) FROM Discount d " +
            "LEFT JOIN d.vendor v " +
            "LEFT JOIN d.category c " +
            "LEFT JOIN d.coupons cou " +
            "ON cou.date <= :dateTo AND cou.date >= :dateFrom " +
            "GROUP BY d ")
    List<DiscountStatisticsDTO> getDiscountsStatistics(@Param("dateFrom") LocalDateTime dateFrom,
                                                       @Param("dateTo") LocalDateTime dateTo,
                                                       Pageable pageable);

    @Query("SELECT new com.exadel.discount.model.dto.statistics.DiscountStatisticsDTO " +
            "(d.id, d.name, d.viewNumber, COUNT(cou) AS numberOfGettingPromo) FROM Discount d " +
            "LEFT JOIN d.vendor v " +
            "LEFT JOIN d.category c " +
            "LEFT JOIN d.coupons cou " +
            "ON cou.date <= :dateTo AND cou.date >= :dateFrom " +
            "LEFT JOIN d.vendorLocations l " +
            "WHERE l.city.id IN :cityIds " +
            "OR l.city.country.id IN :countryIds " +
            "GROUP BY d")
    List<DiscountStatisticsDTO> getDiscountsStatistics(@Param("dateFrom") LocalDateTime dateFrom,
                                                       @Param("dateTo") LocalDateTime dateTo,
                                                       @Param("cityIds") Iterable<UUID> cityIds,
                                                       @Param("countryIds") Iterable<UUID> countryIds,
                                                       Pageable pageable);
}
