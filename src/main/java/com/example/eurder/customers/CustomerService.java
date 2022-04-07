package com.example.eurder.customers;

import com.example.eurder.customers.dto.CreateCustomerDto;
import com.example.eurder.customers.dto.CustomerCreatedDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerCreatedDto createCustomer(CreateCustomerDto createCustomerDto) {
        logger.info("method createCustomer is called");

        // Check on email address copied from Digibuggy
        if(!createCustomerDto.getEmailAddress().matches("^(\\S+)@(\\S+)\\.([a-zA-Z]+)$")) {
            logger.error(new IncorrectEmailFormatException().getMessage());
            throw new IncorrectEmailFormatException();
        }

        Customer customerToCreate = customerMapper.toCustomer(createCustomerDto);

        logger.info("newly created customer is returned to client");
        return customerMapper.toCustomerCreatedDto(customerRepository.createCustomer(customerToCreate));
    }
}
