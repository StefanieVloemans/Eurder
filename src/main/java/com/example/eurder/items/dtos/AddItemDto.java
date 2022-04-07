package com.example.eurder.items.dtos;

public class AddItemDto {
    private final String itemName;
    private final String itemDescription;
    private final double price;
    private final int amount;

    public AddItemDto(String itemName, String itemDescription, double price, int amount) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.amount = amount;
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
