package com.example.eurder.customers;

import com.example.eurder.customers.dtos.CreateCustomerDto;
import com.example.eurder.customers.dtos.CustomerDto;
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
class CustomerControllerIntegrationTests {
    @LocalServerPort
    private int port;

    @Test
    void givenCustomerDetails_WhenCreateCustomerIsCalled_ThenNewCustomerIsAddedInDatabaseClass() {
        //GIVEN
        CreateCustomerDto customerToCreate = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart","Simpson","bart@simpson.com").build();

        //WHEN
        CustomerDto newCustomer = RestAssured
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
                .as(CustomerDto.class);
        //THEN
        Assertions.assertThat(newCustomer.getFirstName()).isEqualTo(customerToCreate.getFirstName());
        // Next line redundant:
        Assertions.assertThat(newCustomer.getFirstName()).isNotNull().isNotBlank().isNotEmpty();
        Assertions.assertThat(newCustomer.getLastName()).isEqualTo(customerToCreate.getLastName());
        Assertions.assertThat(newCustomer.getLastName()).isNotNull().isNotBlank().isNotEmpty();
        Assertions.assertThat(newCustomer.getEmailAddress()).isEqualTo(customerToCreate.getEmailAddress());
        Assertions.assertThat(newCustomer.getEmailAddress()).isNotNull().isNotBlank().isNotEmpty();
        Assertions.assertThat(newCustomer.getCustomerId()).isNotNull();
    }
}