package com.discount.java11.Repository;

import com.discount.java11.Entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByPrice(int price);
//    @Query("select c from Orders c " +
//            "where lower(c.serialNumber) like lower(concat('%', :searchTerm, '%'))")
//    List<Order> findBySerialNumber(String serialNumber);

    @Query(value = "select a from orders a where a.seriual_number= :('%', :searchTerm, '%')", nativeQuery = true)
    List<Order> findBySerialNumber(@Param("searchTerm") String searchTerm);
}
