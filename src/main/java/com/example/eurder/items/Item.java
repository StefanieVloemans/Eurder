package com.example.eurder.items;

import java.util.UUID;

public class Item {
    private final String itemId;
    private final String itemName;
    private final String itemDescription;
    private final double price;
    private final int amount;

    public Item(String itemName, String itemDescription, double price, int amount) {
        itemId = UUID.randomUUID().toString();
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

}
