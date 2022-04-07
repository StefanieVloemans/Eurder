package com.example.eurder.customers;

import com.example.eurder.customers.dtos.CreateCustomerDto;
import com.example.eurder.customers.dtos.CustomerCreatedDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerMapper {
    public Customer toCustomer(CreateCustomerDto customerToCreate) {
        return new Customer.CustomerBuilder(
                UUID.randomUUID().toString(),
                customerToCreate.getFirstName(),
                customerToCreate.getLastName(),
                customerToCreate.getEmailAddress()).build();
    }

    public CustomerCreatedDto toCustomerCreatedDto(Customer customer) {
        return new CustomerCreatedDto(customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmailAddress());
    }
}
