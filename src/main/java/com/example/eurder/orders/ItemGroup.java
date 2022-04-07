package com.example.eurder.orders;

import java.time.LocalDate;

public class ItemGroup {
    private final String itemId;
    private final int amount;
    private LocalDate shippingDate;

    public ItemGroup(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }
}
