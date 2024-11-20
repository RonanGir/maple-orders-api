package com.maplr.test.sugarshack.mapleordersapi.cart;

import lombok.Builder;

@Builder
public record CreateCartRequest(
        Long customerId
) {
}
