package com.example.eurder.customers;

import com.example.eurder.customers.dto.CreateCustomerDto;
import com.example.eurder.customers.dto.CustomerCreatedDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
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

    @Test
    void GivenEmailAddressWithIncorrectFormat_WhenCreateCustomerIsCalled_ThenBadRequestIsReturnedWithCustomMessage() {
        //GIVEN
        CreateCustomerDto customerToCreate = new CreateCustomerDto(
                "Bart",
                "Simpson",
                "bart@simpsoncom",
                "Street",
                "1",
                "Springfield",
                "012/3456789");
        //WHEN + THEN
                RestAssured
                .given()
                .body(customerToCreate)
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
