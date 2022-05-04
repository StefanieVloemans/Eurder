package com.example.eurder.items;

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
    }

    private void validateIfItemNameAlreadyExists(Item itemToAdd) {
        if (itemRepository.doesItemNameAlreadyExist(itemToAdd)) {
            logger.error(new ItemWithThisNameAlreadyExistsException().getMessage());
            throw new ItemWithThisNameAlreadyExistsException();
        }
    }
}
