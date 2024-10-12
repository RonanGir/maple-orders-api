package com.maplr.test.sugarshack.mapleordersapi.model.dto.cart;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.CustomerDto;
import com.maplr.test.sugarshack.mapleordersapi.model.enums.TransactionEnum;
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
