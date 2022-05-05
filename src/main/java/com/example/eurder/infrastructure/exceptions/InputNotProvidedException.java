package com.example.eurder.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputNotProvidedException extends RuntimeException {
    public InputNotProvidedException(String missingInput) {
        super(missingInput + " not provided");
    }
}
