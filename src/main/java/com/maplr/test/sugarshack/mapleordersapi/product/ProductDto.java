package com.maplr.test.sugarshack.mapleordersapi.product;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record ProductDto(
        @NotNull String description,
        @NotNull String id,
        @NotNull String image,
        @NotNull String name,
        @NotNull Double price,
        @NotNull Integer stock,
        @NotNull MapleSyrupTypeEnum type,
        Integer maxQty,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {

}
