package com.example.eurder.items.dtos;

public class AddItemDto {
    private String itemName;
    private String itemDescription;
    private double price;
    private int amount;

    public AddItemDto(String itemName, String itemDescription, double price, int amount) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.amount = amount;
    }

    public AddItemDto() {
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
