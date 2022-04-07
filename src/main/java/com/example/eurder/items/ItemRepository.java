package com.example.eurder.items;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
    private final ItemDatabase itemDatabase;

    public ItemRepository(ItemDatabase itemDatabase) {
        this.itemDatabase = itemDatabase;
    }


}
