package com.example.eurder.orders;

import org.springframework.stereotype.Repository;

import java.util.Properties;

@Repository
public class OrderRepository {
    private final OrderDatabase orderDatabase;

    public OrderRepository(OrderDatabase orderDatabase) {
        this.orderDatabase = orderDatabase;
    }

    public double placeOrder(Order order) {
        return orderDatabase.saveOrder(order);
    }
}
