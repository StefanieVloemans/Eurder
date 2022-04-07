package com.example.eurder.customers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectEmailFormatException extends RuntimeException {
    public IncorrectEmailFormatException() {
        super("The Email Address does not have the correct format");
    }
}
