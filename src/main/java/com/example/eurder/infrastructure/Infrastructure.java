package com.example.eurder.infrastructure;

import com.example.eurder.items.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Infrastructure {
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public static void logAndThrowError(RuntimeException exception) {
        logger.error(exception.getMessage());
        throw exception;
    }
}
