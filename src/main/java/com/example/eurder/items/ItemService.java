package com.example.eurder.items;

import com.example.eurder.items.dtos.AddItemDto;
import com.example.eurder.items.dtos.ItemAddedDto;
import com.example.eurder.items.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public ItemAddedDto addItem(AddItemDto addItemDto) {
        logger.info("method addItem is called");

        if (addItemDto.getItemName() == null || addItemDto.getItemName().isEmpty() || addItemDto.getItemName().isBlank()) {
            logger.error(new NoItemProvidedException().getMessage());
            throw new NoItemProvidedException();
        }

        if (addItemDto.getItemDescription() == null || addItemDto.getItemDescription().isEmpty() || addItemDto.getItemDescription().isBlank()) {
            logger.error(new NoDescriptionProvidedException().getMessage());
            throw new NoDescriptionProvidedException();
        }

        if (addItemDto.getPrice() <= 0) {
            logger.error(new InvalidPriceException().getMessage());
            throw new InvalidPriceException();
        }

        if (addItemDto.getAmount() < 0) {
            logger.error(new InvalidAmountException().getMessage());
            throw new InvalidAmountException();
        }

        Item itemToAdd = itemMapper.toItem(addItemDto);

        if (itemRepository.checkIfItemNameAlreadyExists(itemToAdd).isPresent()) {
            logger.error(new ItemWithThisNameAlreadyExistsException().getMessage());
            throw new ItemWithThisNameAlreadyExistsException();
        }

        logger.info("Created item is returned");
        return itemMapper.toItemAddedDto(itemRepository.addItem(itemToAdd));
    }
}
