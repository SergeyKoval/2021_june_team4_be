package com.exadel.discound.controller;

import com.exadel.discound.dto.OrderDto;
import com.exadel.discound.entity.Order;
import com.exadel.discound.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        Order order = orderService.addOrder(Order.from(orderDto));
        return new ResponseEntity<>(OrderDto.from(order), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> allOrders = orderService.findAllOrders();
        List<OrderDto> allOrdersDto = allOrders.stream().map(OrderDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(allOrdersDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable final Long id) {
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(OrderDto.from(order), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable final Long id) {
        Order order = orderService.deleteOrder(id);
        return new ResponseEntity<>(OrderDto.from(order), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderDto> editOrder(@PathVariable final long id,
                                              @RequestBody final OrderDto orderDto) {
        Order editedOrder = orderService.editOrder(id, Order.from(orderDto));
        return new ResponseEntity<>(OrderDto.from(editedOrder), HttpStatus.OK);
    }
}
