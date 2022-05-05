package com.example.eurder.items;

import com.example.eurder.items.dtos.AddItemDto;
import com.example.eurder.items.dtos.ItemDto;
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
class ItemControllerIntegrationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void givenItemDetails_WhenAddItemIsCalled_ThenNewItemIsAddedInDatabase() {
        AddItemDto addItemDto = new AddItemDto("Donut", "Sweet snack with hole in the middle", 19.99, 50);

        ItemDto addedItemDto = RestAssured
                .given()
                .body(addItemDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ItemDto.class);

        org.junit.jupiter.api.Assertions.assertFalse(addedItemDto.getItemId().isEmpty());
        org.junit.jupiter.api.Assertions.assertFalse(addedItemDto.getItemId().isBlank());
        Assertions.assertThat(addedItemDto.getItemName()).isEqualTo(addItemDto.getItemName());
        Assertions.assertThat(addedItemDto.getItemDescription()).isEqualTo(addItemDto.getItemDescription());
        Assertions.assertThat(addedItemDto.getPrice()).isEqualTo(addItemDto.getPrice());
        Assertions.assertThat(addedItemDto.getAmount()).isEqualTo(addItemDto.getAmount());
    }

    @Nested
    @DisplayName("Input Validation tests")
    class InputValidationTest {
        @Test
        void givenItemNameNotUnique_WhenAddItemIsCalled_ThenBadRequestIsReturned() {
            Item alreadyExistingItem = new Item("Donut", "sweety sweetness", 3.99, 10);
            itemRepository.addItem(alreadyExistingItem);

            Item itemDuplicateName = new Item("Donut", "pastry with hole in the middle", 3.59, 5);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemDuplicateName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void WhenAddItemIsCalledWithoutItemNameProvided_ThenBadRequestIsReturned() {
            Item itemWithoutName = new Item(null, "sweety sweetness", 3.99, 10);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void WhenAddItemIsCalledWithNameEmpty_ThenBadRequestIsReturned() {
            Item itemWithoutName = new Item("", "sweety sweetness", 3.99, 10);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void WhenAddItemIsCalledWithNameBlank_ThenBadRequestIsReturned() {
            Item itemWithoutName = new Item(" ", "sweety sweetness", 3.99, 10);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void WhenAddItemIsCalledWithoutDescriptionProvided_ThenBadRequestIsReturned() {
            Item itemWithoutName = new Item("donut", null, 3.99, 10);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void WhenAddItemIsCalledWithDescriptionEmpty_ThenBadRequestIsReturned() {
            Item itemWithoutName = new Item("Donut", "", 3.99, 10);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void WhenAddItemIsCalledWithDescriptionBlank_ThenBadRequestIsReturned() {
            Item itemWithoutName = new Item("Donut", " ", 3.99, 10);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void WhenAddItemIsCalledWithoutPriceProvided_ThenBadRequestIsReturned() {
            Item itemWithoutName = new Item("donut", "description", 0, 10);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void WhenAddItemIsCalledWithNegativePrice_ThenBadRequestIsReturned() {
            Item itemWithoutName = new Item("donut", "description", -1, 10);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void WhenAddItemIsCalledWithNegativeAmount_ThenBadRequestIsReturned() {
            Item itemWithoutName = new Item("Donut", "description", 3.99, -1);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }
}