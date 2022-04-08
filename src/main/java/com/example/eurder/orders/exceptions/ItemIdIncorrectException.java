package com.example.eurder.orders.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemIdIncorrectException extends RuntimeException {
    public ItemIdIncorrectException() {
        super("No item linked to item id ");
    }
}
