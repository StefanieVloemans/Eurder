package com.example.eurder.customers;

import com.example.eurder.customers.dtos.CreateCustomerDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerControllerUnitTests {
    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void WhenCreateCustomerIsCalledWithoutProvidingFirstName_ThenBadRequestIsReturnedWithCustomMessage() {
        //GIVEN
        CreateCustomerDto customerWithoutFirstName = new CreateCustomerDto.CreateCustomerDtoBuilder("","Simpson","bart@simpson.com").build();

        //WHEN + THEN
        RestAssured
                .given()
                .body(customerWithoutFirstName)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void WhenCreateCustomerIsCalledWithoutProvidingLastName_ThenBadRequestIsReturnedWithCustomMessage() {
        //GIVEN
        CreateCustomerDto customerWithoutLastName = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart","","bart@simpson.com").build();

        //WHEN + THEN
        RestAssured
                .given()
                .body(customerWithoutLastName)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void WhenCreateCustomerIsCalledWithoutEmailAddress_ThenBadRequestIsReturnedWithCustomMessage() {
        //GIVEN
        CreateCustomerDto customerWithoutLastName = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart","Simpson", null).build();

        //WHEN + THEN
        RestAssured
                .given()
                .body(customerWithoutLastName)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void WhenCreateCustomerIsCalledWithEmailAddressIsEmpty_ThenBadRequestIsReturnedWithCustomMessage() {
        //GIVEN
        CreateCustomerDto customerWithoutLastName = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart","Simpson", "").build();

        //WHEN + THEN
        RestAssured
                .given()
                .body(customerWithoutLastName)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void WhenCreateCustomerIsCalledWithEmailAddressIsBlank_ThenBadRequestIsReturnedWithCustomMessage() {
        //GIVEN
        CreateCustomerDto customerWithoutLastName = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart","Simpson", " ").build();

        //WHEN + THEN
        RestAssured
                .given()
                .body(customerWithoutLastName)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void GivenEmailAddressWithIncorrectFormat_WhenCreateCustomerIsCalled_ThenBadRequestIsReturnedWithCustomMessage() {
        //GIVEN
        CreateCustomerDto customerIncorrectEmailAddress = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart",
                "Simpson",
                "bart@simpsoncom").build();

        //WHEN + THEN
        RestAssured
                .given()
                .body(customerIncorrectEmailAddress)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void GivenEmailAddressNotUnique_WhenCreateCustomerIsCalled_ThenBadRequestIsReturnedWithCustomMessage() {
        //GIVEN
        CreateCustomerDto alreadyExistingCustomer = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart",
                "Simpson",
                "bart@simpson.com").build();
        customerRepository.createCustomer(customerMapper.toCustomer(alreadyExistingCustomer));

        CreateCustomerDto customerDuplicateEmailAddress = new CreateCustomerDto.CreateCustomerDtoBuilder("Homer",
                "Simpson",
                "bart@simpson.com").build();

        //WHEN + THEN
        RestAssured
                .given()
                .body(customerDuplicateEmailAddress)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
