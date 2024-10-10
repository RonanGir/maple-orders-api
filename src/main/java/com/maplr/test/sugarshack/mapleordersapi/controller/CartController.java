package com.maplr.test.sugarshack.mapleordersapi.controller;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartLineDto;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartModificationDto;
import com.maplr.test.sugarshack.mapleordersapi.service.cart.CartItemService;
import com.maplr.test.sugarshack.mapleordersapi.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/api")
public class CartController {

    private CartItemService itemService;
    private CartService service;


    @Autowired
    public CartController(
            CartItemService itemService,
            CartService service
    ) {
        this.itemService = itemService;
        this.service = service;
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<List<CartLineDto>> getCartItemsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemService.findAllByCartId(id));
    }

    @PutMapping("/cart/{id}")
    public ResponseEntity<Void> addToCart(
            @RequestParam("productId") Long productId,
            @PathVariable("id") Long cartId,
            @RequestParam("userId") Long userId,
            @RequestParam("qty") Integer qty
    ) {
        CartModificationDto cartModificationDto = CartModificationDto.builder()
                .cartId(cartId)
                .qty(qty)
                .userId(userId)
                .productId(productId)
                .build();
        service.addToCart(cartModificationDto);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Void> removeFromCart(
            @RequestParam("productId") Long productId,
            @PathVariable("id") Long cartId
    ) {
        CartModificationDto cartModificationDto = CartModificationDto.builder()
                .cartId(cartId)
                .productId(productId)
                .build();
        service.deleteFromCart(cartModificationDto);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/cart/{id}")
    public ResponseEntity<Void> changeQty(
            @PathVariable("id") Long cartId,
            @RequestParam("productId") Long productId,
            @RequestParam("newQty") Integer newQty
    ) {
        CartModificationDto cartModificationDto = CartModificationDto.builder()
                .cartId(cartId)
                .qty(newQty)
                .productId(productId)
                .build();

        if (newQty <= 0) {
            service.deleteFromCart(cartModificationDto);
        } else {
            itemService.changeQuantity(cartModificationDto);
        }

        return ResponseEntity.accepted().build();
    }
}
