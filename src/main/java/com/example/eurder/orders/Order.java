package com.example.eurder.orders;

import com.example.eurder.customers.Customer;
import com.example.eurder.item_group.ItemGroup;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name ="order_overview")
public class Order {
    @Id
    private String id;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name="fk_order_id")
    private List<ItemGroup> itemGroupList;

    @ManyToOne
    @JoinColumn(name="fk_customer_id")
    private Customer customer;

    @Transient
    private double totalPrice;

    public Order(List<ItemGroup> itemGroupList, Customer customer, double totalPrice){
        this.id = UUID.randomUUID().toString();
        this.totalPrice = totalPrice;
        this.itemGroupList = itemGroupList;
        this.customer = customer;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", itemGroupList=" + itemGroupList +
                ", customer=" + customer +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
