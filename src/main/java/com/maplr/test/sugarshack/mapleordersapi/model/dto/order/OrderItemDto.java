package com.maplr.test.sugarshack.mapleordersapi.model.dto.order;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.product.ProductDto;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record OrderItemDto(
        Long id,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Integer quantity,
        Float totalPrice,
        ProductDto productDto,
        Long orderId,
        Long customerId
) {
}
