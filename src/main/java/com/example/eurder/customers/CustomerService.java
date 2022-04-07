package com.example.eurder.customers;

import com.example.eurder.customers.dto.CreateCustomerDto;
import com.example.eurder.customers.dto.CustomerCreatedDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerCreatedDto createCustomer(CreateCustomerDto createCustomerDto) {
        Customer customerToCreate = customerMapper.toCustomer(createCustomerDto);

        return customerMapper.toCustomerCreatedDto(customerRepository.createCustomer(customerToCreate));
    }
}
