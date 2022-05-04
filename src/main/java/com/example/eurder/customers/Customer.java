package com.example.eurder.customers;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    private String id;
    @Column( name ="FIRST_NAME")
    private String firstName;
    @Column( name ="LAST_NAME")
    private String lastName;
    @Column( name ="EMAIL_ADDRESS")
    private String emailAddress;
    @Column( name ="STREET_NAME")
    private String streetName;
    @Column( name ="STREET_NUMBER")
    private String streetNumber;
    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name ="FK_POSTALCODE_CITY")
    private PostalcodeCity postalcodeCity;
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;

    public Customer() {
    }

    private Customer(CustomerBuilder builder) {
        this.id = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.streetName = builder.streetName;
        this.streetNumber = builder.streetNumber;
        this.postalcodeCity = builder.postalcodeCity;
        this.phoneNumber = builder.phoneNumber;
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

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class CustomerBuilder {
        private final String customerId;
        private final String firstName;
        private final String lastName;
        private final String emailAddress;
        private String streetName;
        private String streetNumber;
        private PostalcodeCity postalcodeCity;
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

        public CustomerBuilder setPostalcodeCity(PostalcodeCity postalcodeCity) {
            this.postalcodeCity = postalcodeCity;
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
