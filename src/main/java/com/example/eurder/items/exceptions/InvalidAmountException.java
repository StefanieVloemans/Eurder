package com.example.eurder.items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException() {
        super("Price cannot be negative");
    }
}
