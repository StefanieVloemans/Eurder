package com.example.eurder.items;

import com.example.eurder.items.dtos.AddItemDto;
import com.example.eurder.items.dtos.ItemAddedDto;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemDatabase itemDatabase;

    public ItemService(ItemMapper itemMapper, ItemDatabase itemDatabase) {
        this.itemMapper = itemMapper;
        this.itemDatabase = itemDatabase;
    }

    public ItemAddedDto addItem(AddItemDto addItemDto) {
        Item itemToAdd = itemMapper.toItem(addItemDto);
        return itemMapper.toItemAddedDto(itemDatabase.saveItem(itemToAdd));
    }
}
