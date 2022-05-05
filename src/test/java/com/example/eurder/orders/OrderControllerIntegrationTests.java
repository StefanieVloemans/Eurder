package com.example.eurder.orders;

import com.example.eurder.customers.Customer;
import com.example.eurder.customers.CustomerRepository;
import com.example.eurder.items.Item;
import com.example.eurder.items.ItemRepository;
import com.example.eurder.item_group.dtos.ItemGroupDto;
import com.example.eurder.orders.dtos.OrderDto;
import com.example.eurder.orders.dtos.PlaceOrderDto;
import com.example.eurder.orders.exceptions.IdIncorrectException;
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
class OrderControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void givenOneOrMoreItemGroups_WhenPlaceOrderIsCalled_ThenOrderIsAddedInDatabase() {
        //GIVEN
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

        //WHEN
        OrderDto orderDto = RestAssured
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

        //THEN
        Assertions.assertThat(orderDto.getId()).isNotNull().isNotEmpty().isNotBlank();
    }

    @Test
    void givenAnIncorrectItemId_WhenPlaceOrderIsCalled_ThenBadRequestIsThrown() {
        //GIVEN
        Customer existingCustomer = new Customer.CustomerBuilder("1","Alex","Turner","alex@turner.be").build();
        customerRepository.createCustomer(existingCustomer);
        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(new ItemGroupDto("1", 2), new ItemGroupDto("2", 5)), existingCustomer.getId());

        //WHEN
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

        Throwable thrown = Assertions.catchThrowable(() -> orderService.placeOrder(placeOrderDto));

        //THEN
        Assertions.assertThat(thrown)
                .isInstanceOf(IdIncorrectException.class)
                .hasMessage("Unknown item id");
    }

    @Test
    void givenAnIncorrectCustomerId_WhenPlaceOrderIsCalled_ThenBadRequestIsThrown() {
        //GIVEN
        Item existingItem1 = new Item("item1","description1", 2.5, 4);
        itemRepository.addItem(existingItem1);
        Item existingItem2 = new Item("item1","description1", 2.5, 4);
        itemRepository.addItem(existingItem2);

        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(new ItemGroupDto(existingItem1.getId(), 2), new ItemGroupDto(existingItem2.getId(), 5)), "non-existing customer id");

        //WHEN
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

        Throwable thrown = Assertions.catchThrowable(() -> orderService.placeOrder(placeOrderDto));

        //THEN
        Assertions.assertThat(thrown)
                .isInstanceOf(IdIncorrectException.class)
                .hasMessage("Unknown customer id");
    }

    @Test
    void givenAmountIsBiggerThanAmountToOrder_WhenPlaceOrderIsCalled_ThenShippingDateIsTodayPlus1Week() {
        Item existingItem = new Item("item1","description1", 5.99, 3);
        itemRepository.addItem(existingItem);

        ItemGroupDto itemGroup1 = new ItemGroupDto(existingItem.getId(), 1);
        Customer existingCustomer = new Customer.CustomerBuilder("1","Alex","Turner","alex@turner.be").build();
        customerRepository.createCustomer(existingCustomer);
        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(itemGroup1), existingCustomer.getId());

        OrderDto actualOrderDto = RestAssured
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

//        Assertions.assertThat(actualOrderDto.getItemGroupList().get(0).getShippingDate()).isEqualTo(LocalDate.now().plusDays(1));
        Assertions.assertThat(actualOrderDto.getItemGroupList().get(0).getShippingDate()).isEqualTo(LocalDate.now().plusDays(1));
    }

    @Test
    void givenAmountInStockSmallerThanAmountToOrder_WhenPlaceOrderIsCalled_ThenShippingDateIsTodayPlus1Week() {
        Item existingItem = new Item("item1","description1", 5.99, 1);
        itemRepository.addItem(existingItem);

        ItemGroupDto itemGroup1 = new ItemGroupDto(existingItem.getId(), 3);
        Customer existingCustomer = new Customer.CustomerBuilder("1","Alex","Turner","alex@turner.be").build();
        customerRepository.createCustomer(existingCustomer);
        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(itemGroup1), existingCustomer.getId());

        OrderDto orderDto = RestAssured
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

        Assertions.assertThat(orderDto.getItemGroupList().get(0).getShippingDate()).isEqualTo(LocalDate.now().plusWeeks(1));
    }

    @Test
    void givenAmount_WhenPlaceOrderIsCalled_ThenItemAmountIsSubstractedWithOrderedAmount() {
//        Item existingItem = new Item("item1","description1", 5.99, 3);
//        itemRepository.addItem(existingItem);
//        ItemGroupDto itemGroup1 = new ItemGroupDto(existingItem.getId(), 2);
//        Customer existingCustomer = new Customer.CustomerBuilder("1","Alex","Turner","alex@turner.be").build();
//        customerRepository.createCustomer(existingCustomer);
//        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(itemGroup1), existingCustomer.getId());
        Item existingItem = new Item("item1","description1", 5.99, 3);
        itemRepository.addItem(existingItem);

        ItemGroupDto itemGroup1 = new ItemGroupDto(existingItem.getId(), 2);
        Customer existingCustomer = new Customer.CustomerBuilder("1","Alex","Turner","alex@turner.be").build();
        customerRepository.createCustomer(existingCustomer);
        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(itemGroup1), existingCustomer.getId());
        OrderDto orderDto = RestAssured
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

//        Assertions.assertThat(itemRepository.findById(orderDto.getItemGroupList().get(0).getItem().getId()).
//                getAmount()).isEqualTo(1);
        Assertions.assertThat(orderDto.getItemGroupList().get(0).getItem().getAmount()).isEqualTo(1);
    }

    @Test
    void givenListOf1ItemGroup_WhenPlaceOrderIsCalled_ThenCorrectPriceIsReturned() {
        int itemGroupAmount = 3;
        double itemGroupPrice = 5.99;

        Item existingItem = new Item("item1","description1", itemGroupPrice, 4);
        itemRepository.addItem(existingItem);

        ItemGroupDto itemGroup1 = new ItemGroupDto(existingItem.getId(), itemGroupAmount);
        Customer existingCustomer = new Customer.CustomerBuilder("1","Alex","Turner","alex@turner.be").build();
        customerRepository.createCustomer(existingCustomer);
        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(itemGroup1), existingCustomer.getId());

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

        Assertions.assertThat(order.getTotalPrice()).isEqualTo(itemGroupAmount * itemGroupPrice);
    }

    @Test
    void givenListOf3ItemGroups_WhenPlaceOrderIsCalled_ThenCorrectPriceIsReturned() {
        int itemGroupAmount1 = 1;
        double itemGroupPrice1 = 1.1;
        int itemGroupAmount2 = 2;
        double itemGroupPrice2 = 2.2;
        int itemGroupAmount3 = 3;
        double itemGroupPrice3 = 3.3;
        Item existingItem1 = new Item("item1","description1", itemGroupPrice1, 5);
        itemRepository.addItem(existingItem1);
        Item existingItem2 = new Item("item2","description2", itemGroupPrice2, 5);
        itemRepository.addItem(existingItem2);
        Item existingItem3 = new Item("item3","description3", itemGroupPrice3, 5);
        itemRepository.addItem(existingItem3);

        ItemGroupDto itemGroup1 = new ItemGroupDto(existingItem1.getId(), itemGroupAmount1);
        ItemGroupDto itemGroup2 = new ItemGroupDto(existingItem2.getId(), itemGroupAmount2);
        ItemGroupDto itemGroup3 = new ItemGroupDto(existingItem3.getId(), itemGroupAmount3);
        Customer existingCustomer = new Customer.CustomerBuilder("1","Alex","Turner","alex@turner.be").build();
        customerRepository.createCustomer(existingCustomer);

        PlaceOrderDto placeOrderDto = new PlaceOrderDto(List.of(itemGroup1, itemGroup2, itemGroup3), existingCustomer.getId());

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

        Assertions.assertThat(order.getTotalPrice()).isEqualTo(itemGroupAmount1 * itemGroupPrice1 + itemGroupAmount2 * itemGroupPrice2 + itemGroupAmount3 * itemGroupPrice3);
    }
}