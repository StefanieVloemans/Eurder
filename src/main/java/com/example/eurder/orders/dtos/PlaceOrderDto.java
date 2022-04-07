package com.example.eurder.orders.dtos;

import com.example.eurder.orders.ItemGroup;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderDto {
    private final ItemGroup[] itemGroups;

    public PlaceOrderDto(ItemGroup[] itemGroups) {
        this.itemGroups = itemGroups;
    }

    public ItemGroup[] getItemGroups() {
        return itemGroups;
    }
}
