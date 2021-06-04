package com.discount.java11.Service;

import com.discount.java11.Entity.Order;
import com.discount.java11.Exception.OrderNotFoundAtPriceException;
import com.discount.java11.Exception.OrderNotFoundException;
import com.discount.java11.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public List<Order> findAllOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderByPrice(int price) {
        List<Order> orders= StreamSupport.stream(orderRepository.findByPrice(price).spliterator(), false)
                .collect(Collectors.toList());
        if (orders.isEmpty()) throw new OrderNotFoundAtPriceException(price);
    return orders;
    }
}
