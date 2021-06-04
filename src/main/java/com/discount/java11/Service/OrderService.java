package com.discount.java11.Service;

import com.discount.java11.Entity.Order;
import com.discount.java11.Entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order findOrderById(Long id);

    List<Order> findAllOrders();

    List<Order> findOrderByPrice(int price);

}
