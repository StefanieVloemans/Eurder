package com.example.eurder.orders;

import com.example.eurder.orders.dtos.ItemGroupDto;
import com.example.eurder.orders.dtos.OrderDto;
import com.example.eurder.orders.dtos.PlaceOrderDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {


    public Order toOrder(PlaceOrderDto placeOrderDto) {
        List<ItemGroup> itemGroups = new ArrayList<>();
        for(ItemGroupDto itemGroupDto : placeOrderDto.getItemGroupDto()) {
            itemGroups.add(this.toItemGroup(itemGroupDto));
        }
        return new Order(itemGroups);
    }

    private ItemGroup toItemGroup(ItemGroupDto itemGroupDto) {
       return new ItemGroup(itemGroupDto.getItemId(), itemGroupDto.getAmount());
    }

    public OrderDto toOrderDto(Order order) {
        return new OrderDto(0, order.getItemGroups());
    }
}
