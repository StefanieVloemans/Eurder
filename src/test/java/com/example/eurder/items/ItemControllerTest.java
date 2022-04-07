package com.example.eurder.items;

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
class ItemControllerTest {
    @LocalServerPort
    private int port;

//    @Test
//    void givenItemDetails_WhenAddItemIsCalled_ThenNewtemIsAddedInDatabase() {
//        AddItemDto addItemDto = new AddItemDto("Donut", "Delicious desert with hole in the middle", 19.99, 50);
//
//        ItemAddedDto addedItemDto = RestAssured
//                .given()
//                .body(addItemDto)
//                .accept(JSON)
//                .contentType(JSON)
//                .when()
//                .port(port)
//                .post("/items")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.CREATED.value())
//                .extract()
//                .as(ItemAddedDto.class);
//
//        Assertions.assertThat(addedItemDto.getItemName()).isEqualTo(addedItemDto.getItemName);
//    }
}