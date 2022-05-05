package com.example.eurder.customers;

import com.example.eurder.customers.dtos.CreateCustomerDto;
import com.example.eurder.customers.dtos.CustomerDto;
import com.example.eurder.customers.exceptions.EmailNotUniqueException;
import com.example.eurder.customers.exceptions.IncorrectEmailFormatException;
import com.example.eurder.infrastructure.exceptions.InputNotProvidedException;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void givenCustomerDetails_WhenCreateCustomerIsCalled_ThenNewCustomerIsAddedInDatabaseClass() {
        //GIVEN
        CreateCustomerDto customerToCreate = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart", "Simpson", "bart@simpson.com").build();

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

    @Nested
    @DisplayName("Input validation tests")
    class InputValidationTest {
        @Test
        void WhenCreateCustomerIsCalledWithoutProvidingFirstName_ThenBadRequestIsReturnedWithCustomMessage() {
            //GIVEN
            CreateCustomerDto customerWithoutFirstName = new CreateCustomerDto.CreateCustomerDtoBuilder("", "Simpson", "bart@simpson.com").build();

            //WHEN
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

            Throwable thrown = Assertions.catchThrowable(() -> customerService.createCustomer(customerWithoutFirstName));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(InputNotProvidedException.class)
                    .hasMessage("First name not provided");
        }

        @Test
        void WhenCreateCustomerIsCalledWithoutProvidingLastName_ThenBadRequestIsReturnedWithCustomMessage() {
            //GIVEN
            CreateCustomerDto customerWithoutLastName = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart", "", "bart@simpson.com").build();

            //WHEN
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

            Throwable thrown = Assertions.catchThrowable(() -> customerService.createCustomer(customerWithoutLastName));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(InputNotProvidedException.class)
                    .hasMessage("Last name not provided");
        }

        @Nested
        @DisplayName("Email Address validation tests")
        class EmailAddressValidationTest {

            @Test
            void WhenCreateCustomerIsCalledWithoutEmailAddress_ThenBadRequestIsReturnedWithCustomMessage() {
                //GIVEN
                CreateCustomerDto customerWithoutEmailAddress = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart", "Simpson", null).build();

                //WHEN + THEN
                RestAssured
                        .given()
                        .body(customerWithoutEmailAddress)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/customers")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.BAD_REQUEST.value());

                Throwable thrown = Assertions.catchThrowable(() -> customerService.createCustomer(customerWithoutEmailAddress));

                //THEN
                Assertions.assertThat(thrown)
                        .isInstanceOf(InputNotProvidedException.class)
                        .hasMessage("Email address not provided");
            }

            @Test
            void WhenCreateCustomerIsCalledWithEmailAddressIsEmpty_ThenBadRequestIsReturnedWithCustomMessage() {
                //GIVEN
                CreateCustomerDto customerWithoutEmailAddress = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart", "Simpson", "").build();

                //WHEN + THEN
                RestAssured
                        .given()
                        .body(customerWithoutEmailAddress)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/customers")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.BAD_REQUEST.value());

                Throwable thrown = Assertions.catchThrowable(() -> customerService.createCustomer(customerWithoutEmailAddress));

                //THEN
                Assertions.assertThat(thrown)
                        .isInstanceOf(InputNotProvidedException.class)
                        .hasMessage("Email address not provided");
            }

            @Test
            void WhenCreateCustomerIsCalledWithEmailAddressIsBlank_ThenBadRequestIsReturnedWithCustomMessage() {
                //GIVEN
                CreateCustomerDto customerWithoutEmailAddress = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart", "Simpson", " ").build();

                //WHEN + THEN
                RestAssured
                        .given()
                        .body(customerWithoutEmailAddress)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/customers")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.BAD_REQUEST.value());

                Throwable thrown = Assertions.catchThrowable(() -> customerService.createCustomer(customerWithoutEmailAddress));

                //THEN
                Assertions.assertThat(thrown)
                        .isInstanceOf(InputNotProvidedException.class)
                        .hasMessage("Email address not provided");
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

                Throwable thrown = Assertions.catchThrowable(() -> customerService.createCustomer(customerIncorrectEmailAddress));

                //THEN
                Assertions.assertThat(thrown)
                        .isInstanceOf(IncorrectEmailFormatException.class)
                        .hasMessage("The Email Address does not have the correct format");
            }

            @Test
            void GivenEmailAddressNotUnique_WhenCreateCustomerIsCalled_ThenBadRequestIsReturnedWithCustomMessage() {
                //GIVEN
                String alreadyExistingEmailAddress = "bart@simpson.com";
                CreateCustomerDto alreadyExistingCustomer = new CreateCustomerDto.CreateCustomerDtoBuilder("Bart",
                        "Simpson", alreadyExistingEmailAddress
                        ).build();
                customerRepository.createCustomer(customerMapper.toCustomer(alreadyExistingCustomer));

                CreateCustomerDto customerDuplicateEmailAddress = new CreateCustomerDto.CreateCustomerDtoBuilder("Homer",
                        "Simpson",
                        alreadyExistingEmailAddress).build();

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

                Throwable thrown = Assertions.catchThrowable(() -> customerService.createCustomer(customerDuplicateEmailAddress));

                //THEN
                Assertions.assertThat(thrown)
                        .isInstanceOf(EmailNotUniqueException.class)
                        .hasMessage("Email address " + alreadyExistingEmailAddress + " is already linked to a customer account");
            }
        }
    }
}