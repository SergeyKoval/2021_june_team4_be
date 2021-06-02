package com.discount.Repository;

import com.discount.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByPrice(String ticketType);

    List<Order> findByOrderByPerson(boolean isHot);

}
