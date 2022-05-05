package com.example.eurder.items.dtos;

public class ItemDto {
    private String itemId;
    private String itemName;
    private String itemDescription;
    private double price;
    private int amount;

    public ItemDto(String itemId, String itemName, String itemDescription, double price, int amount) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.amount = amount;
    }

    public ItemDto() {
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
