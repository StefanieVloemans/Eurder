package com.example.eurder.item_group;

import com.example.eurder.items.Item;
import com.example.eurder.orders.Order;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "item_group")
public class ItemGroup {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "fk_item_id")
    private Item item;

    @Column(name = "amount")
    private int amount;

    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    public ItemGroup(Item item, int amount, LocalDate shippingDate) {
        this.id = UUID.randomUUID().toString();
        this.item = item;
        this.amount = amount;
        this.shippingDate = shippingDate;
    }

    public ItemGroup() {
    }

    public String getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    @Override
    public String toString() {
        return "ItemGroup{" +
                "id='" + id + '\'' +
                ", item=" + item +
                ", amount=" + amount +
                ", shippingDate=" + shippingDate +
                '}';
    }
}
