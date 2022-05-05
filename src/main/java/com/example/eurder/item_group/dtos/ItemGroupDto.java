package com.example.eurder.item_group.dtos;

public class ItemGroupDto {
    private String itemId;
    private int amount;

    public ItemGroupDto(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public ItemGroupDto() {
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

}
