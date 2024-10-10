package com.maplr.test.sugarshack.mapleordersapi.model.dto.cart;

import lombok.Builder;

@Builder
public record CartModificationDto(
        Long productId,
        Integer qty,
        Long cartId,
        Long userId
) {

}
