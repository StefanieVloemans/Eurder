package com.example.eurder.customers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotUniqueException extends RuntimeException {
    public EmailNotUniqueException(String emailAddress) {
        super("Email address " + emailAddress + " is already linked to a customer account");
    }
}
