package com.example.eurder.items;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemRepository {
    private final ItemDatabase itemDatabase;

    public ItemRepository(ItemDatabase itemDatabase) {
        this.itemDatabase = itemDatabase;
    }


    public Item addItem(Item itemToAdd) {
        return itemDatabase.saveItem(itemToAdd);
    }

    public Optional<Item> checkIfItemNameAlreadyExists(Item item) {
        return itemDatabase.checkIfItemNameAlreadyExists(item);
    }

    public Optional<Item> checkIfItemIdIsKnown(String itemId) {
        return itemDatabase.checkIfItemIdIsKnown(itemId);
    }

    public int getAmountByItemId(String itemId) {
        return itemDatabase.getAmountByItemId(itemId);
    }

    public void reduceAmountAfterOrder(String itemId, int amount) {
        itemDatabase.reduceAmountAfterOrder(itemId, amount);
    }
}
