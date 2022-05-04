package com.example.eurder.items;

import com.example.eurder.items.dtos.AddItemDto;
import com.example.eurder.items.dtos.ItemDto;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item toItem(AddItemDto addItemDto) {
        return new Item (addItemDto.getItemName(),
                addItemDto.getItemDescription(),
                addItemDto.getPrice(),
                addItemDto.getAmount());
    }

    public ItemDto toItemAddedDto(Item item) {
        return new ItemDto(item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getAmount());
    }
}
