package com.example.eurder.customers;

import com.example.eurder.customers.dtos.CreateCustomerDto;
import com.example.eurder.customers.dtos.CustomerDto;
import com.example.eurder.customers.exceptions.*;
import com.example.eurder.infrastructure.Infrastructure;
import com.example.eurder.infrastructure.exceptions.InputNotProvidedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        logger.info("method createCustomer is called");
        Customer customerToCreate = customerMapper.toCustomer(createCustomerDto);

        validateIfFirstAndLastNameArePresent(createCustomerDto);
        validateEmailAddress(createCustomerDto, customerToCreate);

        CustomerDto customerDto = customerMapper.toCustomerCreatedDto(customerRepository.createCustomer(customerToCreate));
        logger.info("newly created customer is returned to client");
        return customerDto;
    }

    private void validateIfFirstAndLastNameArePresent(CreateCustomerDto createCustomerDto) {
        if (createCustomerDto.getFirstName() == null || createCustomerDto.getFirstName().isBlank() || createCustomerDto.getFirstName().isEmpty()) {
            Infrastructure.logAndThrowError(new InputNotProvidedException("First name"));
        }

        if (createCustomerDto.getLastName() == null || createCustomerDto.getLastName().isBlank() || createCustomerDto.getLastName().isEmpty()) {
            Infrastructure.logAndThrowError(new InputNotProvidedException("Last name"));
        }
    }

    private void validateEmailAddress(CreateCustomerDto createCustomerDto, Customer customerToCreate) {
        if (createCustomerDto.getEmailAddress() == null || createCustomerDto.getEmailAddress().isBlank() || createCustomerDto.getEmailAddress().isEmpty() ) {
            Infrastructure.logAndThrowError(new InputNotProvidedException("Email address"));
        }

        // Check on email address copied from Digibuggy
        if (!createCustomerDto.getEmailAddress().matches("^(\\S+)@(\\S+)\\.([a-zA-Z]+)$")) {
            logger.error(new IncorrectEmailFormatException().getMessage());
            throw new IncorrectEmailFormatException();
        }

        if (customerRepository.doesEmailAddressAlreadyExist(customerToCreate)) {
            logger.error(new EmailNotUniqueException(createCustomerDto.getEmailAddress()).getMessage());
            throw new EmailNotUniqueException(createCustomerDto.getEmailAddress());
        }
    }
}
