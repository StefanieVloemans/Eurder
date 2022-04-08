package com.example.eurder.orders;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {
    private final String itemGroupId;
    private final String itemId;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroup(String itemId, int amount) {
        this.itemGroupId = UUID.randomUUID().toString();
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
