package com.maplr.test.sugarshack.mapleordersapi.controller;

import com.maplr.test.sugarshack.mapleordersapi.mapper.CartMapper;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartDto;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartModificationDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartEntity;
import com.maplr.test.sugarshack.mapleordersapi.model.enums.TransactionEnum;
import com.maplr.test.sugarshack.mapleordersapi.service.cart.CartItemService;
import com.maplr.test.sugarshack.mapleordersapi.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${app.api.prefix}/public/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController extends BaseController<CartDto, CartEntity, Long> {

    private CartItemService itemService;
    private CartService service;

    @Autowired
    public CartController(
            CartItemService itemService,
            CartService service,
            CartMapper mapper
    ) {
        super(service, mapper);
        this.itemService = itemService;
        this.service = service;
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<List<CartItemDto>> getCartItemsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemService.findAllByCartId(id));
    }

    @PostMapping("/cart/create")
    public ResponseEntity<CartDto> createCart(@RequestBody Long id) {

        CartDto cartDto = CartDto.builder()
                .status(TransactionEnum.OPENED)
                .build();
        return super.create(cartDto);

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
