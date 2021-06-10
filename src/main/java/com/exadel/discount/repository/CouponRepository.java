package com.exadel.discount.repository;

import com.exadel.discount.entity.Coupon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, UUID> {
    List<Coupon> findByPrice(int price);
//    @Query("select c from Orders c " +
//            "where lower(c.serialNumber) like lower(concat('%', :searchTerm, '%'))")
//    List<Coupon> findBySerialNumber(String serialNumber);

    @Query(value = "select a from coupons a where a.seriual_number= :('%', :searchTerm, '%')", nativeQuery = true)
    List<Coupon> findBySerialNumber(@Param("searchTerm") String searchTerm);
}
