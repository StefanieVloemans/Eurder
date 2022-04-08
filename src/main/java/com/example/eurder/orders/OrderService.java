package com.example.eurder.orders;

import com.example.eurder.orders.dtos.OrderDto;
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

    public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
        Order order = orderMapper.toOrder(placeOrderDto);
        return orderMapper.toOrderDto(orderRepository.placeOrder(order));
    }
}
