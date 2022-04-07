package com.example.eurder.orders;

import com.example.eurder.orders.dtos.OrderPlacedDto;
import com.example.eurder.orders.dtos.PlaceOrderDto;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Test
    void givenOneOrMoreItemGroups_WhenPlaceOrderIsCalled_ThenOrderIsAddedInDatabase() {
       PlaceOrderDto placeOrderDto = new PlaceOrderDto(new ItemGroup[]{new ItemGroup("1", 2), new ItemGroup("2",5)});

        OrderPlacedDto orderPlaced= RestAssured
                .given()
                .body(placeOrderDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(OrderPlacedDto.class);

        Assertions.assertThat(orderPlaced.getTotalPrice()).isNotNull().isPositive();
    }
}