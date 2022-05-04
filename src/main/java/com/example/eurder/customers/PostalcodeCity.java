package com.example.eurder.customers;

import javax.persistence.*;

@Entity
@Table (name="POSTALCODE_CITY")
public class PostalcodeCity {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="ZIP_CODE")
    private String zipCode;

    @Column(name = "CITY")
    private String city;

    public PostalcodeCity() {
    }

    public int getId() {
        return id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }
}
