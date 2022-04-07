package com.example.eurder.customers;

import com.example.eurder.customers.dto.CreateCustomerDto;
import com.example.eurder.customers.dto.CustomerCreatedDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CreateCustomerDto customerToCreate) {
        return new Customer(customerToCreate.getFirstName(),
                customerToCreate.getLastName(),
                customerToCreate.getEmailAddress(),
                customerToCreate.getStreetName(),
                customerToCreate.getStreetNumber(),
                customerToCreate.getCityName(),
                customerToCreate.getPhoneNumber());
    }

    public CustomerCreatedDto toCustomerCreatedDto(Customer customer) {
        return new CustomerCreatedDto(customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmailAddress(),
                customer.getStreetName(),
                customer.getStreetNumber(),
                customer.getCityName(),
                customer.getPhoneNumber());
    }
}
