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

    public OrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void placeOrder(Order order) {
        entityManager.persist(order);
    }

    public Order findById(String id) {
        return entityManager.find(Order.class, id);
    }
}
