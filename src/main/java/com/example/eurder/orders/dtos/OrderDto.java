package com.example.eurder.orders.dtos;

import com.example.eurder.item_group.ItemGroup;

import java.util.List;

public class OrderDto {
    private String id;
    private double totalPrice;
    private List<ItemGroup> itemGroupList;

    public OrderDto(String id, double totalPrice, List<ItemGroup> itemGroupList) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.itemGroupList = itemGroupList;
    }

    public OrderDto() {
    }

    public String getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id='" + id + '\'' +
                ", totalPrice=" + totalPrice +
                ", itemGroupList=" + itemGroupList +
                '}';
    }
}
