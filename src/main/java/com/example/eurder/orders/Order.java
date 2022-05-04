package com.example.eurder.orders;

import com.example.eurder.customers.Customer;
import com.example.eurder.item_group.ItemGroup;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name ="EURDER")
public class Order {
    @Id
    private String id;
    @OneToMany (cascade = CascadeType.PERSIST)
    @JoinColumn(name="FK_EURDER_ID")
    private List<ItemGroup> itemGroupList;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name="FK_CUSTOMER_ID")
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


}
