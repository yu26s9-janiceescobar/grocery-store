package org.yearup.service;

import org.yearup.repository.OrderRepository;

public class OrderService {
    private OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
}
