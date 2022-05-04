package com.example.eurder.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table (name = "ITEM")
public class Item {
    @Id
    private String id;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="PRICE")
    private double price;

    @Column(name="AMOUNT")
    private int amount;

    public Item() {
    }

    public Item(String itemName, String itemDescription, double price, int amount) {
        this.id = UUID.randomUUID().toString();
        this.name = itemName;
        this.description = itemDescription;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
