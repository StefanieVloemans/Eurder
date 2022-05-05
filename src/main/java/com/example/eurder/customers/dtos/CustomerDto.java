package com.example.eurder.customers.dtos;

public class CustomerDto {
    private String customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String streetName;
    private String streetNumber;
    private String cityName;
    private String phoneNumber;

    public CustomerDto(String customerId, String firstName, String lastName, String emailAddress) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;

    }

    public CustomerDto() {
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

//    public void setStreetName(String streetName) {
//        this.streetName = streetName;
//    }
//
//    public void setStreetNumber(String streetNumber) {
//        this.streetNumber = streetNumber;
//    }
//
//    public void setCityName(String cityName) {
//        this.cityName = cityName;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
}
