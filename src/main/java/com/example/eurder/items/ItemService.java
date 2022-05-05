package com.example.eurder.items;

import com.example.eurder.infrastructure.Infrastructure;
import com.example.eurder.infrastructure.exceptions.InputNotProvidedException;
import com.example.eurder.items.dtos.AddItemDto;
import com.example.eurder.items.dtos.ItemDto;
import com.example.eurder.items.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public ItemDto addItem(AddItemDto addItemDto) {
        logger.info("method addItem is called");

        checkIfProvidedInputIsCompleteAndCorrect(addItemDto);

        Item itemToAdd = itemMapper.toItem(addItemDto);

        validateIfItemNameAlreadyExists(itemToAdd);

        logger.info("Created item is returned");
        return itemMapper.toItemAddedDto(itemRepository.addItem(itemToAdd));
    }

    private void checkIfProvidedInputIsCompleteAndCorrect(AddItemDto addItemDto) {
        if (addItemDto.getItemName() == null || addItemDto.getItemName().isEmpty() || addItemDto.getItemName().isBlank()) {
            Infrastructure.logAndThrowError(new InputNotProvidedException("Item name"));
        }

        if (addItemDto.getItemDescription() == null || addItemDto.getItemDescription().isEmpty() || addItemDto.getItemDescription().isBlank()) {
            Infrastructure.logAndThrowError(new InputNotProvidedException("Description"));
        }

        if (addItemDto.getPrice() <= 0) {
            Infrastructure.logAndThrowError(new InputCannotBeZeroOrNegative("Price"));
        }

        if (addItemDto.getAmount() < 0) {
            Infrastructure.logAndThrowError(new InputCannotBeZeroOrNegative("Amount"));
        }
    }

    private void validateIfItemNameAlreadyExists(Item itemToAdd) {
        if (itemRepository.doesItemNameAlreadyExist(itemToAdd)) {
            logger.error(new ItemWithThisNameAlreadyExistsException().getMessage());
            throw new ItemWithThisNameAlreadyExistsException();
        }
    }
}
