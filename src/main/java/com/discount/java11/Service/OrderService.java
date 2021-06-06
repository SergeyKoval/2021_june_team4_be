package com.discount.java11.Service;

import com.discount.java11.Entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order findOrderById(Long id);

    Order deleteOrder(Long id);

    List<Order> findAllOrders();

    Order addOrder(Order order);

    List<Order> findOrderByPrice(int price);

    List<Order> findOrderBySerialNumber(String serialNumber);

    Order editOrder(Long id, Order order);

}
