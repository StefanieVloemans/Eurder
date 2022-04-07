package com.example.eurder.items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoDescriptionProvidedException extends RuntimeException {
    public NoDescriptionProvidedException() {
        super("Please provide an item description");
    }
}
