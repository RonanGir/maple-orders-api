package com.maplr.test.sugarshack.mapleordersapi.model.dto.product;

import com.maplr.test.sugarshack.mapleordersapi.model.enums.MapleSyrupTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MapleSyrupDto(
        @NotNull String description,
        @NotNull String id,
        @NotNull String image,
        @NotNull String name,
        @NotNull Double price,
        @NotNull Integer stock,
        @NotNull MapleSyrupTypeEnum type
) {
}
