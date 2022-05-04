package com.example.eurder.orders;

import com.example.eurder.customers.Customer;
import com.example.eurder.item_group.ItemGroup;
import com.example.eurder.orders.dtos.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OrderMapper {

    // MOVE LOGIC FROM MAPPER TO ORDER CLASS (FOR INSTANCE THE LOGIC TO CALCULATE THE TOTAL PRICE, SHIPPING DATE)

    public Order toOrder(List<ItemGroup> itemGroupList, Customer customer, double totalPrice) {
        return new Order(itemGroupList, customer, totalPrice);
    }

    public OrderDto toOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getTotalPrice(), order.getItemGroupList());
    }
}
