package com.maplr.test.sugarshack.mapleordersapi.model.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderLineDto(
        @NotNull Long cartId,
        @NotNull Long userId
) {
}
