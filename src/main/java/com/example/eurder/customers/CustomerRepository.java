package com.example.eurder.customers;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {
    private final CustomerDatabase customerDatabase;

    public CustomerRepository(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public Customer createCustomer(Customer customerToCreate) {
        return customerDatabase.saveCustomer(customerToCreate);
    }
}
