package com.example.eurder.orders;

import com.example.eurder.items.ItemRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderDatabase {
    private final ConcurrentHashMap<String, Order> orderDatabase = new ConcurrentHashMap<>();
    private final ItemRepository itemRepository;

    public OrderDatabase(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Order saveOrder(Order order) {
        orderDatabase.put(order.getOrderId(), order);
        for (ItemGroup itemGroup : order.getItemGroups()) {
            itemRepository.reduceAmountAfterOrder(itemGroup.getItemId(), itemGroup.getAmount());
        }
        return orderDatabase.get(order.getOrderId());
    }
}
