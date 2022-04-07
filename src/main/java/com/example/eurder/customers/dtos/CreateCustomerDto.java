package com.example.eurder.customers.dtos;

public class CreateCustomerDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String streetName;
    private String streetNumber;
    private String cityName;
    private String phoneNumber;

    public CreateCustomerDto() {
    }

    private CreateCustomerDto(CreateCustomerDto builder) {
        this.firstName = builder. firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.streetName = builder.streetName;
        this.streetNumber = builder.streetNumber;
        this.cityName = builder.cityName;
        this.phoneNumber = builder.phoneNumber;
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




    public static class CreateCustomerDtoBuilder {
        private final String firstName;
        private final String lastName;
        private final String emailAddress;
        private String streetName;
        private String streetNumber;
        private String cityName;
        private String phoneNumber;

        public CreateCustomerDtoBuilder(String firstName, String lastName, String emailAddress) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.emailAddress = emailAddress;
        }

        public CreateCustomerDtoBuilder setStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public CreateCustomerDtoBuilder setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public CreateCustomerDtoBuilder setCityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public CreateCustomerDtoBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CreateCustomerDto build() {
            return new CreateCustomerDto();
        }
    }
}
