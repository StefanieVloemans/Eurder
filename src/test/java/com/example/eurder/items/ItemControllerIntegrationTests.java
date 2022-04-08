package com.example.eurder.items;

import com.example.eurder.items.dtos.AddItemDto;
import com.example.eurder.items.dtos.ItemDto;
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
class ItemControllerIntegrationTests {
    @LocalServerPort
    private int port;

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
        Assertions.assertThat(addedItemDto.getItemName()).isEqualTo(addedItemDto.getItemName());
        Assertions.assertThat(addedItemDto.getItemDescription()).isEqualTo(addedItemDto.getItemDescription());
        Assertions.assertThat(addedItemDto.getPrice()).isEqualTo(addedItemDto.getPrice());
        Assertions.assertThat(addedItemDto.getAmount()).isEqualTo(addedItemDto.getAmount());
    }
}