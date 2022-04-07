package com.example.eurder.items;

import com.example.eurder.items.dtos.AddItemDto;
import com.example.eurder.items.dtos.ItemAddedDto;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item toItem(AddItemDto addItemDto) {
        return new Item (addItemDto.getItemName(),
                addItemDto.getItemDescription(),
                addItemDto.getPrice(),
                addItemDto.getAmount());
    }

    public ItemAddedDto toItemAddedDto(Item item) {
        return new ItemAddedDto(item.getItemId(),
                item.getItemName(),
                item.getItemDescription(),
                item.getPrice(),
                item.getAmount());
    }
}
