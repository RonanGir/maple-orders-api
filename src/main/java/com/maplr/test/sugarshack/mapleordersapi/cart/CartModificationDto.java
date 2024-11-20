package com.maplr.test.sugarshack.mapleordersapi.cart;

import lombok.Builder;

@Builder
public record CartModificationDto(
        Long productId,
        Integer qty,
        Long cartId,
        Long userId
) {

}
