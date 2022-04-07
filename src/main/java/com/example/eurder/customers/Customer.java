package com.example.eurder.customers;

import java.util.UUID;

public class Customer {
    private final String customerId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String streetName;
    private final String streetNumber;
    private final String cityName;
    private final String phoneNumber;

    public Customer(String firstName,
                              String lastName,
                              String emailAddress,
                              String streetName,
                              String streetNumber,
                              String cityName,
                              String phoneNumber) {
        this.customerId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.cityName = cityName;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
