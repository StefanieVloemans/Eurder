package com.example.eurder.orders;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderDatabase {
    private final ConcurrentHashMap<String, Order> orderDatabase = new ConcurrentHashMap<>();

    public Order saveOrder(Order order) {
        orderDatabase.put(order.getOrderId(), order);
        return orderDatabase.get(order.getOrderId());
    }
}
