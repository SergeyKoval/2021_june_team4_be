package com.discount.Service;

import com.discount.Entity.Order;
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
