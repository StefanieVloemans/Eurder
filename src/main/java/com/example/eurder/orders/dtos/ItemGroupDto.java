package com.example.eurder.orders.dtos;

public class ItemGroupDto {
    private final String itemId;
    private final int amount;

    public ItemGroupDto(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

}
