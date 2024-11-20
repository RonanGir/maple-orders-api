package com.maplr.test.sugarshack.mapleordersapi.cart.item;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CartItemDto(
        @NotNull String image,
        @NotNull String name,
        @NotNull Double price,
        @NotNull Long productId,
        @NotNull Long cartId,
        @NotNull Integer qty

) {
}
