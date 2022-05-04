package com.example.eurder.customers;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CustomerRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Customer createCustomer(Customer customerToCreate) {
        entityManager.persist(customerToCreate);
        return customerToCreate;
    }

    public boolean doesEmailAddressAlreadyExist(Customer customer) {
         List<Customer> customerList = entityManager.createQuery("select c from Customer c where c.emailAddress = :emailaddress", Customer.class)
        .setParameter("emailaddress", customer.getEmailAddress())
                 .getResultList();
         return customerList.size() > 0;
    }

    public Customer findById(String customerId) {
        return entityManager.find(Customer.class, customerId);
    }
}
