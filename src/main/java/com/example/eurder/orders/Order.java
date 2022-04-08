package com.example.eurder.orders;

import java.util.List;
import java.util.UUID;

public class Order {
    private final double totalPrice;
    private final String orderId;
    private final List<ItemGroup> itemGroupList;

    public Order(double totalPrice, List<ItemGroup> itemGroupList){
        this.orderId = UUID.randomUUID().toString();
        this.totalPrice = totalPrice;
        this.itemGroupList = itemGroupList;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroupList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
