package com.example.eurder.items;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class ItemDatabase {
    ConcurrentHashMap<String, Item> itemDatabase = new ConcurrentHashMap<>();

    public Item saveItem(Item itemToAdd) {
        itemDatabase.put(itemToAdd.getItemId(), itemToAdd);
        return itemDatabase.get(itemToAdd.getItemId());
    }

    public Optional<Item> checkIfItemNameAlreadyExists(Item itemToCheck) {
        return itemDatabase.values().stream()
                .filter(item -> item.getItemName().equalsIgnoreCase(itemToCheck.getItemName()))
                .findFirst();
    }

    public Optional<Item> checkIfItemIdIsKnown(String itemId) {
        return itemDatabase.values().stream()
                .filter(item -> item.getItemId().equalsIgnoreCase(itemId))
                .findFirst();
    }

    public int getAmountByItemId(String itemId) {

        return itemDatabase.get(itemId).getAmount();
    }
}
