package com.maplr.test.sugarshack.mapleordersapi.model.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CartLineDto(
        @NotNull String image,
        @NotNull String name,
        @NotNull Double price,
        @NotNull String productId,
        @NotNull Integer qty

) {
}
