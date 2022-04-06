package com.example.eurder.customers;

import com.example.eurder.customers.dto.CreateCustomerDto;
import com.example.eurder.customers.dto.CustomerCreatedDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerCreatedDto createCustomer(@RequestBody CreateCustomerDto customerToCreate){
        return new CustomerCreatedDto("1", "Bart", "Simpson","bart@simpson.com","Street","1", "Springfield", "012/3456789" );
    }
}
