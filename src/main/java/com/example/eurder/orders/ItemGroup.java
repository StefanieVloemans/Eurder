package com.example.eurder.orders;

import java.time.LocalDate;

public class ItemGroup {
    private final String itemId;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroup(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
        shippingDate = LocalDate.now();
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
