package com.example.eurder.customers;

import com.example.eurder.customers.dtos.CreateCustomerDto;

import java.util.UUID;

public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String streetName;
    private String streetNumber;
    private String cityName;
    private String phoneNumber;

    public Customer() {
    }

    private Customer (CustomerBuilder builder) {
        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.streetName = builder.streetName;
        this.streetNumber = builder.streetNumber;
        this.cityName = builder.cityName;
        this.phoneNumber = builder.phoneNumber;
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

    public static class CustomerBuilder{
        private final String customerId;
        private final String firstName;
        private final String lastName;
        private final String emailAddress;
        private String streetName;
        private String streetNumber;
        private String cityName;
        private String phoneNumber;

        public CustomerBuilder(String customerId, String firstName, String lastName, String emailAddress) {
            this.customerId = customerId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.emailAddress = emailAddress;
        }

        public CustomerBuilder setStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public CustomerBuilder setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }
        public CustomerBuilder setCityName(String cityName) {
            this.cityName = cityName;
            return this;
        }
        public CustomerBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
