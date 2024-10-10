package com.maplr.test.sugarshack.mapleordersapi.controller;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.OrderValidationResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api/order")
public class OrderController {

    public ResponseEntity<OrderValidationResponseDto> placeOrder() {
        return ResponseEntity.ok(null);
    }

}
