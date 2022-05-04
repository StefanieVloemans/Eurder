package com.example.eurder.item_group;

import com.example.eurder.item_group.dtos.ItemGroupDto;
import com.example.eurder.items.Item;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ItemGroupMapper {

    public ItemGroup toItemGroup(Item item, ItemGroupDto itemGroupDto, LocalDate shippingDate) {
        return new ItemGroup(item, itemGroupDto.getAmount(), shippingDate);
    }

}
