package com.example.eurder.items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputCannotBeZeroOrNegative extends RuntimeException {
    public InputCannotBeZeroOrNegative(String incorrectInput) {
        super(incorrectInput + " cannot be zero or negative");
    }
}
