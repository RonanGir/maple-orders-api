package com.maplr.test.sugarshack.mapleordersapi.order;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderLineDto(
        @NotNull Long cartId,
        @NotNull Long userId
) {
}
