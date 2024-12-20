package com.maplr.test.sugarshack.mapleordersapi.order;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderValidationResponseDto(
        @NotNull Boolean isOrderValid,
        List<String> errors
) {
}
