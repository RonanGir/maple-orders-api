package com.maplr.test.sugarshack.mapleordersapi.model.dto;

import com.maplr.test.sugarshack.mapleordersapi.model.enums.MapleSyrupTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CatalogueItemDto(
        @NotNull String id,
        @NotNull String image,
        @NotNull String name,
        @NotNull Double price,
        @NotNull Integer maxQty,
        @NotNull MapleSyrupTypeEnum type
) {
}
