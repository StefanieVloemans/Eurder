package com.example.eurder.items;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ItemRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public ItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Item addItem(Item itemToAdd) {
        entityManager.persist(itemToAdd);
        return itemToAdd;
    }

    public boolean doesItemNameAlreadyExist(Item item) {
        List<Item> itemList = entityManager.createQuery("select i from Item i where i.name = :name", Item.class)
                .setParameter("name", item.getName())
                .getResultList();
        return itemList.size() > 0;
    }

    public boolean isItemIdKnown(String itemId) {
        List<Item> itemList = entityManager.createQuery("select i from Item i where i.id = :id", Item.class)
                .setParameter("id", itemId)
                .getResultList();
        return itemList.size() > 0;
    }

    public Item findById(String itemId) {
        return entityManager.find(Item.class, itemId);
    }
}
