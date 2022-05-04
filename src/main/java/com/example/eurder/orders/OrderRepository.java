package com.example.eurder.orders;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class OrderRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Order placeOrder(Order order) {
        entityManager.persist(order);
        return order;
    }
}
