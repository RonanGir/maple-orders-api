package com.maplr.test.sugarshack.mapleordersapi.controller;

import com.maplr.test.sugarshack.mapleordersapi.common.BaseMapper;
import com.maplr.test.sugarshack.mapleordersapi.common.CrudService;
import com.maplr.test.sugarshack.mapleordersapi.order.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${app.api.prefix}/public/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController extends BaseController<OrderDto, OrderEntity, Long> {

    private OrderService orderService;

    @Autowired
    public OrderController(
            CrudService<OrderEntity, Long> service,
            BaseMapper<OrderEntity, OrderDto> mapper,
            OrderService orderService
    ) {
        super(service, mapper);
        this.orderService = orderService;
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDto>> getOrdersByUser(@PathVariable("id") Long customerId) {
        return ResponseEntity.ok(this.orderService.getOrderBy(customerId));
    }

    @PostMapping
    public ResponseEntity<OrderValidationResponseDto> placeOrder(@RequestBody OrderLineDto order) {
        return ResponseEntity.ok(orderService.placeOrder(order.userId(), order.cartId()));
    }

}
