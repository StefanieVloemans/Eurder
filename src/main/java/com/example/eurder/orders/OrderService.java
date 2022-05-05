package com.example.eurder.orders;

import com.example.eurder.customers.Customer;
import com.example.eurder.customers.CustomerRepository;
import com.example.eurder.infrastructure.Infrastructure;
import com.example.eurder.item_group.ItemGroup;
import com.example.eurder.item_group.ItemGroupMapper;
import com.example.eurder.items.Item;
import com.example.eurder.items.ItemRepository;
import com.example.eurder.items.ItemService;
import com.example.eurder.item_group.dtos.ItemGroupDto;
import com.example.eurder.orders.dtos.OrderDto;
import com.example.eurder.orders.dtos.PlaceOrderDto;
import com.example.eurder.orders.exceptions.ItemIdIncorrectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderMapper orderMapper;
    private final ItemGroupMapper itemGroupMapper;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);
    private final CustomerRepository customerRepository;

    public OrderService(OrderMapper orderMapper, ItemGroupMapper itemGroupMapper, OrderRepository orderRepository, ItemRepository itemRepository, CustomerRepository customerRepository) {
        this.orderMapper = orderMapper;
        this.itemGroupMapper = itemGroupMapper;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
    }

    public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
        List<ItemGroupDto> itemGroupDtos = placeOrderDto.getItemGroupDto();
        itemGroupValidation(itemGroupDtos);

        double totalPrice = calculateOrderPrice(itemGroupDtos);
        List<ItemGroup> itemGroupList = fromItemGroupDtoListToItemGroupList(placeOrderDto);
        Customer customer = customerRepository.findById(placeOrderDto.getCustomerId());
        Order order = orderMapper.toOrder(itemGroupList, customer, totalPrice);

        orderRepository.placeOrder(order);
        OrderDto orderDtoToReturn = orderMapper.toOrderDto(order);

        for (ItemGroup itemGroup : itemGroupList) {
            Item item = itemGroup.getItem();
            item.setAmount(item.getAmount() - itemGroup.getAmount());
        }

        logger.info("Order placing is finished, returning orderDto to client");
        return orderDtoToReturn;
    }

    private List<ItemGroup> fromItemGroupDtoListToItemGroupList(PlaceOrderDto placeOrderDto) {
        // toch terug in Mapper steken?
        List<ItemGroup> itemGroupList = new ArrayList<>();
        for (ItemGroupDto itemGroupDto : placeOrderDto.getItemGroupDto()) {
            LocalDate shippingDate = calculateShippingDate(itemGroupDto);
            Item item = itemRepository.findById(itemGroupDto.getItemId());
            itemGroupList.add(itemGroupMapper.toItemGroup(item, itemGroupDto, shippingDate));
        }
        return itemGroupList;
    }

    private void itemGroupValidation(List<ItemGroupDto> itemGroupDtos) {
        boolean isItemPresentInDb = false;
        for (ItemGroupDto itemGroupDto : itemGroupDtos) {
            if (itemRepository.isItemIdKnown(itemGroupDto.getItemId())) {
                isItemPresentInDb = true;
            }
        }
        if (!isItemPresentInDb) {
            Infrastructure.logAndThrowError(new ItemIdIncorrectException());
        }
    }

    public LocalDate calculateShippingDate(ItemGroupDto itemGroupDto) {
        LocalDate shippingDate;
        if (itemRepository.findById(itemGroupDto.getItemId()).getAmount() - itemGroupDto.getAmount() < 0) {
            shippingDate = LocalDate.now().plusWeeks(1);
        } else {
            shippingDate = LocalDate.now().plusDays(1);
        }
        return shippingDate;
    }

    public double calculateOrderPrice(List<ItemGroupDto> itemGroupDtoList) {
        double totalPrice = 0;
        for (ItemGroupDto itemGroupDto : itemGroupDtoList) {
            totalPrice = itemGroupDto.getAmount() * itemRepository.findById(itemGroupDto.getItemId()).getPrice();
        }
        return totalPrice;
    }
}
