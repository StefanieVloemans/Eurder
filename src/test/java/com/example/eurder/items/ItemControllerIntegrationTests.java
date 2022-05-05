package com.example.eurder.items;

import com.example.eurder.infrastructure.exceptions.InputNotProvidedException;
import com.example.eurder.items.dtos.AddItemDto;
import com.example.eurder.items.dtos.ItemDto;
import com.example.eurder.items.exceptions.InputCannotBeZeroOrNegative;
import com.example.eurder.items.exceptions.ItemWithThisNameAlreadyExistsException;
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
    private ItemService itemService;

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
        void givenItemNameNotUnique_WhenAddItemIsCalled_ThenBadRequestIsReturnedAndExceptionIsThrown() {
            Item alreadyExistingItem = new Item("Donut", "sweety sweetness", 3.99, 10);
            itemRepository.addItem(alreadyExistingItem);

            AddItemDto itemDtoDuplicateName = new AddItemDto("Donut", "pastry with hole in the middle", 3.59, 5);

            //WHEN
            RestAssured
                    .given()
                    .body(itemDtoDuplicateName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

            Throwable thrown = Assertions.catchThrowable(() -> itemService.addItem(itemDtoDuplicateName));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(ItemWithThisNameAlreadyExistsException.class)
                    .hasMessage("An item with this name already exists");
        }

        @Test
        void WhenAddItemIsCalledWithoutItemNameProvided_ThenBadRequestIsReturnedAndExceptionIsThrown() {
            AddItemDto itemDtoWithoutName = new AddItemDto(null, "sweety sweetness", 3.99, 10);

            //WHEN
            RestAssured
                    .given()
                    .body(itemDtoWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

            Throwable thrown = Assertions.catchThrowable(() -> itemService.addItem(itemDtoWithoutName));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(InputNotProvidedException.class)
                    .hasMessage("Item name not provided");
        }

        @Test
        void WhenAddItemIsCalledWithNameEmpty_ThenBadRequestIsReturnedAndExceptionIsThrown() {
            AddItemDto itemDtoWithoutName = new AddItemDto("", "sweety sweetness", 3.99, 10);

            //WHEN
            RestAssured
                    .given()
                    .body(itemDtoWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

            Throwable thrown = Assertions.catchThrowable(() -> itemService.addItem(itemDtoWithoutName));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(InputNotProvidedException.class)
                    .hasMessage("Item name not provided");
        }

        @Test
        void WhenAddItemIsCalledWithNameBlank_ThenBadRequestIsReturnedAndExceptionIsThrown() {
            AddItemDto itemDtoWithoutName = new AddItemDto("  ", "sweety sweetness", 3.99, 10);

            //WHEN
            RestAssured
                    .given()
                    .body(itemDtoWithoutName)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

            Throwable thrown = Assertions.catchThrowable(() -> itemService.addItem(itemDtoWithoutName));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(InputNotProvidedException.class)
                    .hasMessage("Item name not provided");
        }

        @Test
        void WhenAddItemIsCalledWithoutDescriptionProvided_ThenBadRequestIsReturnedAndExceptionIsThrown() {
            AddItemDto itemDtoWithoutDescription = new AddItemDto("donut", null, 3.99, 10);

            //WHEN
            RestAssured
                    .given()
                    .body(itemDtoWithoutDescription)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

            Throwable thrown = Assertions.catchThrowable(() -> itemService.addItem(itemDtoWithoutDescription));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(InputNotProvidedException.class)
                    .hasMessage("Description not provided");
        }

        @Test
        void WhenAddItemIsCalledWithDescriptionEmpty_ThenBadRequestIsReturnedAndExceptionIsThrown() {
            AddItemDto itemDtoWithoutDescription = new AddItemDto("donut", "", 3.99, 10);

            //WHEN
            RestAssured
                    .given()
                    .body(itemDtoWithoutDescription)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

            Throwable thrown = Assertions.catchThrowable(() -> itemService.addItem(itemDtoWithoutDescription));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(InputNotProvidedException.class)
                    .hasMessage("Description not provided");
        }

        @Test
        void WhenAddItemIsCalledWithDescriptionBlank_ThenBadRequestIsReturnedAndExceptionIsThrown() {
            AddItemDto itemDtoWithoutDescription = new AddItemDto("donut", " ", 3.99, 10);

            //WHEN
            RestAssured
                    .given()
                    .body(itemDtoWithoutDescription)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

            Throwable thrown = Assertions.catchThrowable(() -> itemService.addItem(itemDtoWithoutDescription));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(InputNotProvidedException.class)
                    .hasMessage("Description not provided");
        }

        @Test
        void WhenAddItemIsCalledWithPriceEqualTo0_ThenBadRequestIsReturnedAndExceptionIsThrown() {
            AddItemDto itemDtoWithPrice0 = new AddItemDto("donut", "description", 0, 10);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemDtoWithPrice0)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

            Throwable thrown = Assertions.catchThrowable(() -> itemService.addItem(itemDtoWithPrice0));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(InputCannotBeZeroOrNegative.class)
                    .hasMessage("Price cannot be zero or negative");
        }

        @Test
        void WhenAddItemIsCalledWithNegativePrice_ThenBadRequestIsReturnedAndExceptionIsThrown() {
            AddItemDto itemDtoWithNegativePrice = new AddItemDto("donut", "description", -1, 10);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemDtoWithNegativePrice)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

            Throwable thrown = Assertions.catchThrowable(() -> itemService.addItem(itemDtoWithNegativePrice));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(InputCannotBeZeroOrNegative.class)
                    .hasMessage("Price cannot be zero or negative");
        }

        @Test
        void WhenAddItemIsCalledWithNegativeAmount_ThenBadRequestIsReturnedAndExceptionIsThrown() {
            AddItemDto itemDtoWithNegativeAmount = new AddItemDto("Donut", "description", 3.99, -1);

            //WHEN + THEN
            RestAssured
                    .given()
                    .body(itemDtoWithNegativeAmount)
                    .accept(JSON)
                    .contentType(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

            Throwable thrown = Assertions.catchThrowable(() -> itemService.addItem(itemDtoWithNegativeAmount));

            //THEN
            Assertions.assertThat(thrown)
                    .isInstanceOf(InputCannotBeZeroOrNegative.class)
                    .hasMessage("Amount cannot be zero or negative");
        }
    }
}