package com.example.eurder.orders.dtos;

import java.util.List;

public class PlaceOrderDto {
    private List<ItemGroupDto> itemGroupDto;

    public PlaceOrderDto(List<ItemGroupDto> itemGroupDto) {
        this.itemGroupDto = itemGroupDto;
    }

    public PlaceOrderDto() {
    }

    public List<ItemGroupDto> getItemGroupDto() {
        return itemGroupDto;
    }
}
