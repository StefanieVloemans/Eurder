package com.example.eurder.customers;

import com.example.eurder.customers.dtos.CreateCustomerDto;
import com.example.eurder.customers.dtos.CustomerCreatedDto;
import com.example.eurder.customers.exceptions.*;
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
        Customer customerToCreate = customerMapper.toCustomer(createCustomerDto);

        validateIfFirstAndLastNameArePresent(createCustomerDto);
        validateEmailAddress(createCustomerDto, customerToCreate);

        logger.info("newly created customer is returned to client");
        return customerMapper.toCustomerCreatedDto(customerRepository.createCustomer(customerToCreate));
    }

    private void validateIfFirstAndLastNameArePresent(CreateCustomerDto createCustomerDto) {
        if (createCustomerDto.getFirstName() == null || createCustomerDto.getFirstName().isBlank() || createCustomerDto.getFirstName().isEmpty()) {
            logger.error(new FirstNameNotProvidedException().getMessage());
            throw new FirstNameNotProvidedException();
        }

        if (createCustomerDto.getLastName() == null || createCustomerDto.getLastName().isBlank() || createCustomerDto.getLastName().isEmpty()) {
            logger.error(new LastNameNotProvidedException().getMessage());
            throw new LastNameNotProvidedException();
        }
    }

    private void validateEmailAddress(CreateCustomerDto createCustomerDto, Customer customerToCreate) {
        if(createCustomerDto.getEmailAddress() == null) {
            logger.error(new NoEmailProvidedException().getMessage());
            throw new NoEmailProvidedException();
        }

        // Check on email address copied from Digibuggy
        if(!createCustomerDto.getEmailAddress().matches("^(\\S+)@(\\S+)\\.([a-zA-Z]+)$")) {
            logger.error(new IncorrectEmailFormatException().getMessage());
            throw new IncorrectEmailFormatException();
        }

        if(customerRepository.checkIfEmailAddressAlreadyExists(customerToCreate).isPresent()){
            logger.error(new EmailNotUniqueException(createCustomerDto.getEmailAddress()).getMessage());
            throw new EmailNotUniqueException(createCustomerDto.getEmailAddress());
        }
    }
}
