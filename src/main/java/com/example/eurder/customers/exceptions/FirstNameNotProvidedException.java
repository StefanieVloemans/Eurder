package com.example.eurder.customers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FirstNameNotProvidedException extends RuntimeException {
    public FirstNameNotProvidedException() {
        super("Please provide a first name");
    }
}
