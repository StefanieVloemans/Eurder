package com.example.eurder.customers.dtos;

import com.example.eurder.customers.PostalcodeCity;

public class ViewAllCustomersDto {
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    public ViewAllCustomersDto(String id, String firstName, String lastName, String emailAddress, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public ViewAllCustomersDto() {
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

}
