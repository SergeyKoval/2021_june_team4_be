package com.exadel.discount.repository;

import com.exadel.discount.model.dto.statistics.CategoryStatisticsDTO;
import com.exadel.discount.model.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @EntityGraph(attributePaths = {"discounts"})
    Optional<Category> findById(UUID uuid);

    @Query("SELECT new com.exadel.discount.model.dto.statistics.CategoryStatisticsDTO " +
            "(c.id, c.name, COUNT(d) AS discountsNumber, " +
            "CASE " +
            "   WHEN(COUNT(d) > 0) THEN SUM(d.viewNumber) " +
            "   ELSE 0 " +
            "END AS viewNumber, " +
            "COUNT(cou) AS numberOfGettingPromo) " +
            "FROM Category c " +
            "LEFT JOIN c.discounts d " +
            "LEFT JOIN d.coupons cou " +
            "ON cou.date <= :dateTo AND cou.date >= :dateFrom " +
            "GROUP BY c ")
    List<CategoryStatisticsDTO> getCategoriesStatistics(@Param("dateFrom") LocalDateTime dateFrom,
                                                        @Param("dateTo") LocalDateTime dateTo,
                                                        Pageable pageable);

    @Query("SELECT new com.exadel.discount.model.dto.statistics.CategoryStatisticsDTO " +
            "(c.id, c.name, COUNT(d) AS discountsNumber, " +
            "CASE " +
            "   WHEN(COUNT(d) > 0) THEN SUM(d.viewNumber) " +
            "   ELSE 0 " +
            "END AS viewNumber, " +
            "COUNT(cou) AS numberOfGettingPromo) " +
            "FROM Category c " +
            "LEFT JOIN c.discounts d " +
            "LEFT JOIN d.coupons cou " +
            "ON cou.date <= :dateTo AND cou.date >= :dateFrom " +
            "LEFT JOIN d.vendorLocations l " +
            "WHERE l.city.id IN :cityIds " +
            "OR l.city.country.id IN :countryIds " +
            "GROUP BY c ")
    List<CategoryStatisticsDTO> getCategoriesStatistics(@Param("dateFrom") LocalDateTime dateFrom,
                                                        @Param("dateTo") LocalDateTime dateTo,
                                                        @Param("cityIds") Iterable<UUID> cityIds,
                                                        @Param("countryIds") Iterable<UUID> countryIds,
                                                        Pageable pageable);
}
