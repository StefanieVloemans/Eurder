package com.example.eurder.items;

public class AddItemDto {
    private final String itemName;
    private final String description;
    private final double price;
    private final int amount;

    public AddItemDto(String itemName, String description, double price, int amount) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

}
