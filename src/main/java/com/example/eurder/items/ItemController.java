package com.example.eurder.items;

import com.example.eurder.items.dtos.AddItemDto;
import com.example.eurder.items.dtos.ItemAddedDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping (produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemAddedDto addItem(@RequestBody AddItemDto addItemDto) {
        return itemService.addItem(addItemDto);
    }
}
