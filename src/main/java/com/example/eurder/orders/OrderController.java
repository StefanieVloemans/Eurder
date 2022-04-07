package com.example.eurder.orders;

import com.example.eurder.orders.dtos.PlaceOrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public double placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
        return orderService.placeOrder(placeOrderDto);
    }
}
