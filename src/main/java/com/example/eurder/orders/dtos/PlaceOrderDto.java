package com.example.eurder.orders.dtos;

import com.example.eurder.item_group.dtos.ItemGroupDto;

import java.util.List;

public class PlaceOrderDto {
    private List<ItemGroupDto> itemGroupDto;
    private String customerId;

    public PlaceOrderDto(List<ItemGroupDto> itemGroupDto, String customerId) {
        this.itemGroupDto = itemGroupDto;
        this.customerId = customerId;
    }

    public PlaceOrderDto() {
    }

    public List<ItemGroupDto> getItemGroupDto() {
        return itemGroupDto;
    }

    public String getCustomerId() {
        return customerId;
    }
}
