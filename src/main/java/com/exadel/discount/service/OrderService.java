package com.exadel.discount.service;

import com.exadel.discount.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Component
public interface OrderService {
    Order findOrderById(UUID id);

    Order deleteOrder(UUID id);

    List<Order> findAllOrders();

    Order addOrder(Order order);

    List<Order> findOrderByPrice(int price);

    List<Order> findOrderBySerialNumber(String serialNumber);

    Order editOrder(UUID id, Order order);

}
