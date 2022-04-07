package com.example.eurder.items;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class ItemDatabase {
    ConcurrentHashMap<String, Item> itemDatabase = new ConcurrentHashMap<>();

    public Item saveItem(Item itemToAdd) {
        itemDatabase.put(itemToAdd.getItemId(), itemToAdd);
        return itemDatabase.get(itemToAdd.getItemId());
    }
}
