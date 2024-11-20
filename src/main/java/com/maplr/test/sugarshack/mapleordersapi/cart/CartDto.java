package com.maplr.test.sugarshack.mapleordersapi.cart;

import com.maplr.test.sugarshack.mapleordersapi.cart.item.CartItemDto;
import com.maplr.test.sugarshack.mapleordersapi.customer.CustomerDto;
import com.maplr.test.sugarshack.mapleordersapi.common.TransactionEnum;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record CartDto(
        Long id,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Integer quantity,
        Float totalPrice,
        TransactionEnum status,
        CustomerDto customerDto,
        List<CartItemDto> items
) {
}
