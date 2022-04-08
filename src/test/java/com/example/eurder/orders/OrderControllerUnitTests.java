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

import java.time.LocalDate;
import java.util.List;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderControllerUnitTests {

    @LocalServerPort
    private int port;

    @Autowired
    ItemRepository itemRepository;

    @Test
    void givenAnIncorrectItemId_WhenPlaceOrderIsCalled_ThenBadRequestIsThrown() {
        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(new ItemGroupDto("1", 2), new ItemGroupDto("2", 5)));

        RestAssured
                .given()
                .body(placeOrderDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    void givenAmountIsBiggerThanAmountToOrder_WhenPlaceOrderIsCalled_ThenShippingDateIsTodayPlus1Week() {
        Item existingItem = new Item("item1","description1", 5.99, 3);
        itemRepository.addItem(existingItem);

        ItemGroupDto itemGroup1 = new ItemGroupDto(existingItem.getItemId(), 1);

        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(itemGroup1));

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

        Assertions.assertThat(order.getItemGroupList().get(0).getShippingDate()).isEqualTo(LocalDate.now().plusDays(1));
    }

    @Test
    void givenAmountInStockSmallerThanAmountToOrder_WhenPlaceOrderIsCalled_ThenShippingDateIsTodayPlus1Week() {
        Item existingItem = new Item("item1","description1", 5.99, 1);
        itemRepository.addItem(existingItem);

        ItemGroupDto itemGroup1 = new ItemGroupDto(existingItem.getItemId(), 3);

        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(itemGroup1));

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

        Assertions.assertThat(order.getItemGroupList().get(0).getShippingDate()).isEqualTo(LocalDate.now().plusWeeks(1));
    }

    @Test
    void givenAmount_WhenPlaceOrderIsCalled_ThenItemAmountIsSubstractedWithOrderedAmount() {
        Item existingItem = new Item("item1","description1", 5.99, 3);
        itemRepository.addItem(existingItem);

        ItemGroupDto itemGroup1 = new ItemGroupDto(existingItem.getItemId(), 2);

        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(itemGroup1));

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

        Assertions.assertThat(itemRepository.getAmountByItemId(existingItem.getItemId())).isEqualTo(1);
    }

//    @Test
//    void given1ItemGroup_WhenPlaceOrderIsCalled_ThenCorrectPriceIsReturned() {
//        Item existingItem = new Item("item1","description1", 5.99, 3);
//        itemRepository.addItem(existingItem);
//
//        ItemGroupDto itemGroup1 = new ItemGroupDto(existingItem.getItemId(), 2);
//
//        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(itemGroup1));
//
//        OrderDto order = RestAssured
//                .given()
//                .body(placeOrderDto)
//                .accept(JSON)
//                .contentType(JSON)
//                .when()
//                .port(port)
//                .post("/orders")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.CREATED.value())
//                .extract()
//                .as(OrderDto.class);
//
//        Assertions.assertThat(itemRepository.getAmountByItemId(existingItem.getItemId())).isEqualTo(1);
//    }
}
