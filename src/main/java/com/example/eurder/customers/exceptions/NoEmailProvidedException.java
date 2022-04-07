package com.example.eurder.customers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoEmailProvidedException extends RuntimeException {
    public NoEmailProvidedException() {
        super("No email address provided");
    }
}
