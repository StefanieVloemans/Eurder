package com.example.eurder.customers;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomerDatabase {
    ConcurrentHashMap<String, Customer> customerDatabase = new ConcurrentHashMap<>();

    public Customer saveCustomer(Customer customerToCreate) {
        customerDatabase.put(customerToCreate.getCustomerId(), customerToCreate);
        return customerDatabase.get(customerToCreate.getCustomerId());
    }

    public Optional<Customer> checkIfEmailAddressAlreadyExists(Customer customer) {
        return customerDatabase.values().stream()
                .filter(Customer -> Customer.getEmailAddress().equalsIgnoreCase(customer.getEmailAddress()))
                .findFirst();
    }
}
