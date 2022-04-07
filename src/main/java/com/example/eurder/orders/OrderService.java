package com.example.eurder.orders;

import com.example.eurder.orders.dtos.PlaceOrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public double placeOrder(PlaceOrderDto placeOrderDto) {
        Order order = orderMapper.toOrder(placeOrderDto);
        return orderRepository.placeOrder(order);
    }
}
