package com.discount.java11.Service;

import com.discount.java11.Entity.Order;
import com.discount.java11.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findOrderById(Long id) {
        List<Order> orders = StreamSupport.stream(orderRepository.findById(id).stream().spliterator(), false)
                .collect(Collectors.toList());
        return orders.get(0);
    }

    @Override
    public List<Order> findAllOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderByPrice(int price) {
        return StreamSupport.stream(orderRepository.findByPrice(price).stream().spliterator(), false)
                .collect(Collectors.toList());
    }
}
