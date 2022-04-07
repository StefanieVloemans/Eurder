package com.example.eurder.orders;

import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final ItemGroup[] itemGroupList;

    public Order(ItemGroup[] itemGroupList) {
        this.itemGroupList = itemGroupList;
        this.orderId = UUID.randomUUID().toString();
    }

    public String getOrderId() {
        return orderId;
    }

    public ItemGroup[] getItemGroupList() {
        return itemGroupList;
    }
}
