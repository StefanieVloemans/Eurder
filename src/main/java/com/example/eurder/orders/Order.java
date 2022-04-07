package com.example.eurder.orders;

import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final List<ItemGroup> itemGroupList;

    public Order(List<ItemGroup> itemGroupList) {
        this.itemGroupList = itemGroupList;
        this.orderId = UUID.randomUUID().toString();
    }
}
