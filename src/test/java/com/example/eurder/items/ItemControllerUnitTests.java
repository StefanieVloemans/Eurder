package com.example.eurder.items;

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
public class ItemControllerUnitTests {
    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;

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
        Item itemWithoutName = new Item("donut", "description",0, 10);

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
    void WhenAddItemIsCalledWithNegativePrive_ThenBadRequestIsReturned() {
        Item itemWithoutName = new Item("donut", "description",-1, 10);

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
