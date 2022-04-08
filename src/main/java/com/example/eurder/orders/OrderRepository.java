package com.example.eurder.orders;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    private final OrderDatabase orderDatabase;

    public OrderRepository(OrderDatabase orderDatabase) {
        this.orderDatabase = orderDatabase;
    }

    public Order placeOrder(Order order) {
        return orderDatabase.saveOrder(order);
    }
}
