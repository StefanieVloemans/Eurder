package com.example.eurder.customers;

import com.example.eurder.customers.dto.CreateCustomerDto;
import com.example.eurder.customers.dto.CustomerCreatedDto;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomerControllerTest {
    @LocalServerPort
    private int port;

    @Test
    void givenCustomerDetails_WhenCreateCustomerIsCalled_ThenNewCustomerIsAddedInDatabaseClass() {
        //GIVEN
        CreateCustomerDto customerToCreate = new CreateCustomerDto(
        "Bart",
        "Simpson",
        "bart@simpson.com",
        "Street",
        "1",
        "Springfield",
        "012/3456789");

        //WHEN
        CustomerCreatedDto newCustomer = RestAssured
                .given()
                .body(customerToCreate)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CustomerCreatedDto.class);
        //THEN
        Assertions.assertThat(newCustomer.getFirstName()).isEqualTo(customerToCreate.getFirstName());
        Assertions.assertThat(newCustomer.getFirstName()).isNotNull().isNotBlank().isNotEmpty();
        Assertions.assertThat(newCustomer.getLastName()).isEqualTo(customerToCreate.getLastName());
        Assertions.assertThat(newCustomer.getLastName()).isNotNull().isNotBlank().isNotEmpty();
        Assertions.assertThat(newCustomer.getEmailAddress()).isEqualTo(customerToCreate.getEmailAddress());
        Assertions.assertThat(newCustomer.getEmailAddress()).isNotNull().isNotBlank().isNotEmpty();
        Assertions.assertThat(newCustomer.getStreetName()).isEqualTo(customerToCreate.getStreetName());
        Assertions.assertThat(newCustomer.getStreetNumber()).isEqualTo(customerToCreate.getStreetNumber());
        Assertions.assertThat(newCustomer.getCityName()).isEqualTo(customerToCreate.getCityName());
        Assertions.assertThat(newCustomer.getPhoneNumber()).isEqualTo(customerToCreate.getPhoneNumber());
        Assertions.assertThat(newCustomer.getCustomerId()).isNotNull();
    }
}