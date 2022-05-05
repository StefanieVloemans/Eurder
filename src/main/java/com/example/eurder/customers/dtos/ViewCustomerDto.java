package com.example.eurder.customers.dtos;

import com.example.eurder.customers.PostalcodeCity;

public class ViewCustomerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String street_name;
    private String street_number;
    private PostalcodeCity postalcodeCity;

    public ViewCustomerDto(String id, String firstName, String lastName, String emailAddress, String phoneNumber, String street_name, String street_number, PostalcodeCity postalcodeCity) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.street_name = street_name;
        this.street_number = street_number;
        this.postalcodeCity = postalcodeCity;
    }

    public ViewCustomerDto() {
    }

    public String getId() {
        return id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreet_name() {
        return street_name;
    }

    public String getStreet_number() {
        return street_number;
    }

    public PostalcodeCity getPostalcodeCity() {
        return postalcodeCity;
    }
}
