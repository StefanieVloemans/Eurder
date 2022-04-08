package com.example.eurder.orders;

import com.example.eurder.items.ItemRepository;
import com.example.eurder.orders.dtos.ItemGroupDto;
import com.example.eurder.orders.dtos.OrderDto;
import com.example.eurder.orders.dtos.PlaceOrderDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    private final ItemRepository itemRepository;

    public OrderMapper(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Order toOrder(PlaceOrderDto placeOrderDto) {
        List<ItemGroup> itemGroups = new ArrayList<>();
        for (ItemGroupDto itemGroupDto : placeOrderDto.getItemGroupDto()) {
            itemGroups.add(this.toItemGroup(itemGroupDto));
        }
        double totalPrice = 0;
        for (ItemGroupDto itemGroupDto : placeOrderDto.getItemGroupDto()) {
            totalPrice = itemGroupDto.getAmount() * itemRepository.getPriceByItemId(itemGroupDto.getItemId());
        }
        return new Order(totalPrice, itemGroups);
    }

    private ItemGroup toItemGroup(ItemGroupDto itemGroupDto) {

        LocalDate shippingDate;
        if (itemRepository.getAmountByItemId(itemGroupDto.getItemId()) - itemGroupDto.getAmount() < 0) {
            shippingDate = LocalDate.now().plusWeeks(1);
        } else {
            shippingDate = LocalDate.now().plusDays(1);
        }

        return new ItemGroup(itemGroupDto.getItemId(), itemGroupDto.getAmount(), shippingDate);
    }

    public OrderDto toOrderDto(Order order) {
        return new OrderDto(order.getTotalPrice(), order.getItemGroups());
    }
}
