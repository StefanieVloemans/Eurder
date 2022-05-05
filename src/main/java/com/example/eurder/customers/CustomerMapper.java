package com.example.eurder.customers;

import com.example.eurder.customers.dtos.CreateCustomerDto;
import com.example.eurder.customers.dtos.CustomerDto;
import com.example.eurder.customers.dtos.ViewAllCustomersDto;
import com.example.eurder.customers.dtos.ViewCustomerDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public CustomerDto toCustomerCreatedDto(Customer customer) {
        return new CustomerDto(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmailAddress());
    }

    public List<ViewAllCustomersDto> toViewAllCustomersDtoList(List<Customer> customerList) {
        List<ViewAllCustomersDto> viewAllCustomersDtos = new ArrayList<>();
        for (Customer customer : customerList) {
            viewAllCustomersDtos.add(this.toViewAllCustomerDto(customer));
        }
        return viewAllCustomersDtos;
    }

    private ViewAllCustomersDto toViewAllCustomerDto(Customer customer) {
        return new ViewAllCustomersDto(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmailAddress(),
                customer.getPhoneNumber());
    }

    public ViewCustomerDto toViewCustomerDto(Customer customer) {
        return new ViewCustomerDto(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmailAddress(),
                customer.getPhoneNumber(),
                customer.getStreetName(),
                customer.getStreetNumber(),
                customer.getPostalcodeCity());
    }
}
