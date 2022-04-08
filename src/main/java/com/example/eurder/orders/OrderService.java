package com.example.eurder.orders;

import com.example.eurder.items.ItemRepository;
import com.example.eurder.items.ItemService;
import com.example.eurder.orders.dtos.ItemGroupDto;
import com.example.eurder.orders.dtos.OrderDto;
import com.example.eurder.orders.dtos.PlaceOrderDto;
import com.example.eurder.orders.exceptions.ItemIdIncorrectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
        List<ItemGroupDto> itemGroupDtos = placeOrderDto.getItemGroupDto();
        boolean isItemPresentInDb = false;
        for(ItemGroupDto itemGroupDto : itemGroupDtos) {
            if(itemRepository.checkIfItemIdIsKnown(itemGroupDto.getItemId()).isPresent()) {
                isItemPresentInDb = true;
            }
        }
        if (!isItemPresentInDb) {
            logger.error(new ItemIdIncorrectException().getMessage());
            throw new ItemIdIncorrectException();
        }

        Order order = orderMapper.toOrder(placeOrderDto);
        return orderMapper.toOrderDto(orderRepository.placeOrder(order));
    }
}
