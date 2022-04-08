package com.example.eurder.orders;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {
    private final String itemGroupId;
    private final String itemId;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroup(String itemId, int amount, LocalDate shippingDate) {
        this.itemGroupId = UUID.randomUUID().toString();
        this.itemId = itemId;
        this.amount = amount;
        this.shippingDate = shippingDate;
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
