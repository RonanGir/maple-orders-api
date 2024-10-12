package com.maplr.test.sugarshack.mapleordersapi.model.dto.product;

import com.maplr.test.sugarshack.mapleordersapi.model.enums.MapleSyrupTypeEnum;
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
