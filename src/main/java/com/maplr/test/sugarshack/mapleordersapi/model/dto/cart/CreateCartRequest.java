package com.maplr.test.sugarshack.mapleordersapi.model.dto.cart;

import lombok.Builder;

@Builder
public record CreateCartRequest(
        Long customerId
) {
}
