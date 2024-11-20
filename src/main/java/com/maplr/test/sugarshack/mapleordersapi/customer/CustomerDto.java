package com.maplr.test.sugarshack.mapleordersapi.customer;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record CustomerDto(
        Long id,
        String name,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt

) {

}
