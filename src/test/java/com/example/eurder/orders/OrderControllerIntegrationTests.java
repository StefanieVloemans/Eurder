package com.example.eurder.orders;

import com.example.eurder.customers.Customer;
import com.example.eurder.customers.CustomerRepository;
import com.example.eurder.items.Item;
import com.example.eurder.items.ItemRepository;
import com.example.eurder.item_group.dtos.ItemGroupDto;
import com.example.eurder.orders.dtos.OrderDto;
import com.example.eurder.orders.dtos.PlaceOrderDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void givenOneOrMoreItemGroups_WhenPlaceOrderIsCalled_ThenOrderIsAddedInDatabase() {
       Item existingItem1 = new Item("item1","description1", 5.99, 5);
       itemRepository.addItem(existingItem1);
        Item existingItem2 = new Item("item2","description2", 3.99, 3);
        itemRepository.addItem(existingItem2);
        Customer existingCustomer = new Customer.CustomerBuilder("1","Alex","Turner","alex@turner.be").build();
        customerRepository.createCustomer(existingCustomer);
        PlaceOrderDto placeOrderDto = new PlaceOrderDto
                (List.of(new ItemGroupDto(existingItem1.getId(), 2),
                        new ItemGroupDto(existingItem2.getId(),5)),
                        existingCustomer.getId());

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