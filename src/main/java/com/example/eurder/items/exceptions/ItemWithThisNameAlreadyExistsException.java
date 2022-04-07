package com.example.eurder.items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemWithThisNameAlreadyExistsException extends RuntimeException {
    public ItemWithThisNameAlreadyExistsException() {
        super("An item with this name already exists");
    }
}
