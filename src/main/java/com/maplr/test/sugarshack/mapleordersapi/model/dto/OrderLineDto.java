package com.maplr.test.sugarshack.mapleordersapi.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderLineDto(
        @NotNull String productId,
        @NotNull Integer qty
) {
}
