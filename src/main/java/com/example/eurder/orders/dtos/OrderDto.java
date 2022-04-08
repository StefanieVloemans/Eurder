package com.example.eurder.orders.dtos;

import com.example.eurder.orders.ItemGroup;

import java.util.List;

public class OrderDto {
    private final double totalPrice;
    private final List<ItemGroup> itemGroupList;

    public OrderDto(double totalPrice, List<ItemGroup> itemGroupList) {
        this.totalPrice = totalPrice;
        this.itemGroupList = itemGroupList;
    }

    public double getTotalPrice() {
        return 0;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }
}
