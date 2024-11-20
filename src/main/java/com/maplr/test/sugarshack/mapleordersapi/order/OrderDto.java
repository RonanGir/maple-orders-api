package com.maplr.test.sugarshack.mapleordersapi.order;

import com.maplr.test.sugarshack.mapleordersapi.common.TransactionEnum;
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
