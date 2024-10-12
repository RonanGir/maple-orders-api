package com.maplr.test.sugarshack.mapleordersapi.model.dto.order;

import com.maplr.test.sugarshack.mapleordersapi.model.enums.TransactionEnum;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record OrderDto(
        Long id,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Float totalPrice,
        TransactionEnum status
) {
}
