package com.example.eurder.items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPriceException extends RuntimeException {
    public InvalidPriceException() {
        super("Please provide a valid price");
    }
}
