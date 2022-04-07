package com.example.eurder.orders;

import com.example.eurder.orders.dtos.PlaceOrderDto;
import org.springframework.stereotype.Component;

import java.util.AbstractList;
import java.util.List;

@Component
public class OrderMapper {
    public Order toOrder(PlaceOrderDto placeOrderDto) {
        return new Order(new AbstractList<ItemGroup>(List.of("1")) {
        });
    }
}
