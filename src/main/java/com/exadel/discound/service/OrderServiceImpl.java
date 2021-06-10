package com.exadel.discound.service;

import com.exadel.discound.exception.OrderNotFoundAtPriceException;
import com.exadel.discound.exception.OrderNotFoundAtSerialNumberException;
import com.exadel.discound.entity.Order;
import com.exadel.discound.exception.OrderNotFoundException;
import com.exadel.discound.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Order deletedOrder  = null;
        try{
            deletedOrder = findOrderById(id);
        }catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        orderRepository.delete(deletedOrder);
        return deletedOrder;
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public List<Order> findAllOrders() {
        return StreamSupport
                .stream(orderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderByPrice(int price) {
        List<Order> orders = StreamSupport
                .stream(orderRepository.findByPrice(price).spliterator(), false)
                .collect(Collectors.toList());
        if (orders.isEmpty()) throw new OrderNotFoundAtPriceException(price);
        return orders;
    }

    @Override
    public List<Order> findOrderBySerialNumber(String serialNumber) {
        List<Order> orders = StreamSupport
                .stream(orderRepository.findBySerialNumber(serialNumber)
                .spliterator(), false)
                .collect(Collectors.toList());
        if (orders.isEmpty()) throw new OrderNotFoundAtSerialNumberException(serialNumber);
        return orders;
    }

    @Transactional
    @Override
    public Order editOrder(Long id, Order order) {
        Order orderUnderEdition = findOrderById(id);
        orderUnderEdition.setPrice(order.getPrice());
        orderUnderEdition.setSerialNumber(order.getSerialNumber());
        return order;
    }

}
