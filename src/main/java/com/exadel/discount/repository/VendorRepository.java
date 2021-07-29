package com.exadel.discount.repository;

import com.exadel.discount.model.dto.statistics.VendorStatisticsDTO;
import com.exadel.discount.model.entity.Vendor;
import org.springframework.data.domain.Pageable;
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
public interface VendorRepository extends JpaRepository<Vendor, UUID> {

    List<Vendor> findAll();

    @EntityGraph(attributePaths = {"vendorLocations", "vendorLocations.city", "vendorLocations.city.country",
            "discounts", "discounts.category", "discounts.tags", "discounts.discountImages"})
    Optional<Vendor> findById(UUID id);

    List<Vendor> findAllByArchived(boolean archived);

    @EntityGraph(attributePaths = {"vendorLocations", "vendorLocations.city", "vendorLocations.city.country",
            "discounts", "discounts.category", "discounts.tags", "discounts.discountImages"})
    Optional<Vendor> findByIdAndArchived(UUID id, boolean archived);

    @Modifying
    @Query("UPDATE Vendor v SET v.archived=:archived WHERE v.id=:vendorId")
    void setArchivedById(@Param("vendorId") UUID id, @Param("archived") boolean archived);

    @Query("SELECT count(v)>0 FROM Vendor v WHERE v.id=:vendorId AND v.archived=false AND" +
            "(SELECT count(d) FROM Discount d WHERE d.vendor.id=:vendorId AND d.archived=false) = 0")
    boolean existsByIdWithNoDiscounts(@Param("vendorId") UUID vendorId);

    boolean existsByIdAndArchived(UUID id, boolean archived);

    @EntityGraph(attributePaths = {"vendorLocations", "vendorLocations.city", "vendorLocations.city.country"})
    Vendor save(Vendor vendor);

    @Query("SELECT new com.exadel.discount.model.dto.statistics.VendorStatisticsDTO " +
            "(v.id, v.name, COUNT(d) AS discountsNumber, " +
            "CASE " +
            "   WHEN(COUNT(d) > 0) THEN SUM(d.viewNumber) " +
            "   ELSE 0 " +
            "END AS viewNumber, " +
            "COUNT(cou) AS numberOfGettingPromo) FROM Vendor v " +
            "LEFT JOIN v.discounts d " +
            "LEFT JOIN d.coupons cou " +
            "ON cou.date <= :dateTo AND cou.date >= :dateFrom " +
            "GROUP BY v ")
    List<VendorStatisticsDTO> getVendorsStatistics(@Param("dateFrom") LocalDateTime dateFrom,
                                                   @Param("dateTo") LocalDateTime dateTo,
                                                   Pageable pageable);

    @Query("SELECT new com.exadel.discount.model.dto.statistics.VendorStatisticsDTO " +
            "(v.id, v.name, COUNT(d) AS discountsNumber, " +
            "CASE " +
            "   WHEN(COUNT(d) > 0) THEN SUM(d.viewNumber) " +
            "   ELSE 0 " +
            "END AS viewNumber, " +
            "COUNT(cou) AS numberOfGettingPromo) FROM Vendor v " +
            "LEFT JOIN v.discounts d " +
            "LEFT JOIN d.coupons cou " +
            "ON cou.date <= :dateTo AND cou.date >= :dateFrom " +
            "LEFT JOIN d.vendorLocations l " +
            "WHERE (l.city.id IN :cityIds " +
            "OR l.city.country.id IN :countryIds) " +
            "GROUP BY v ")
    List<VendorStatisticsDTO> getVendorsStatistics(@Param("dateFrom") LocalDateTime dateFrom,
                                                   @Param("dateTo") LocalDateTime dateTo,
                                                   @Param("cityIds") Iterable<UUID> cityIds,
                                                   @Param("countryIds") Iterable<UUID> countryIds,
                                                   Pageable pageable);
}
