package com.example.eurder.customers.dtos;

public class CustomerCreatedDto {
    private final String customerId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;


    public CustomerCreatedDto(String customerId,
                              String firstName,
                              String lastName,
                              String emailAddress) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
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

}
