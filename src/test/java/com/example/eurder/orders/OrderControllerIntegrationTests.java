package com.example.eurder.orders;

import com.example.eurder.items.Item;
import com.example.eurder.items.ItemRepository;
import com.example.eurder.orders.dtos.ItemGroupDto;
import com.example.eurder.orders.dtos.OrderDto;
import com.example.eurder.orders.dtos.PlaceOrderDto;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ItemRepository itemRepository;

    @Test
    void givenOneOrMoreItemGroups_WhenPlaceOrderIsCalled_ThenOrderIsAddedInDatabase() {
       Item existingItem = new Item("item1","description1", 5.99, 5);
       itemRepository.addItem(existingItem);
        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(new ItemGroupDto(existingItem.getItemId(), 2), new ItemGroupDto("2",5)));

        OrderDto order = RestAssured
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
                .as(OrderDto.class);

//        Assertions.assertThat(order.getTotalPrice()).isNotNull().isPositive();
    }
}