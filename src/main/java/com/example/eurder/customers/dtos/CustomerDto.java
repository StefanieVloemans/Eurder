package com.example.eurder.customers.dtos;

public class CustomerDto {
    private final String customerId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
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
