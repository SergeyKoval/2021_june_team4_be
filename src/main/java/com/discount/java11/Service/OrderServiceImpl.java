package com.discount.java11.Service;

import com.discount.java11.Entity.Order;
import com.discount.java11.Exception.OrderNotFoundAtPriceException;
import com.discount.java11.Exception.OrderNotFoundAtSerialNumberException;
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

    public Order deleteOrder(Long id) {
        Order order = findOrderById(id);
        orderRepository.delete(order);
        return order;
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
        List<Order> orders = StreamSupport.stream(orderRepository.findByPrice(price).spliterator(), false)
                .collect(Collectors.toList());
        if (orders.isEmpty()) throw new OrderNotFoundAtPriceException(price);
        return orders;
    }

    @Override
    public List<Order> findOrderBySerialNumber(String serialNumber) {
        List<Order> orders = StreamSupport.stream(orderRepository.findBySerialNumber(serialNumber).spliterator(), false)
                .collect(Collectors.toList());
        if (orders.isEmpty()) throw new OrderNotFoundAtSerialNumberException(serialNumber);
        return orders;
    }

    @Override
    public Order editOrder(Long id, Order order) {
        Order orderUnderEdition = findOrderById(id);
        orderUnderEdition.setId(order.getId());
        orderUnderEdition.setPrice(order.getPrice());
        return order;
    }

}
