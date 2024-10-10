package com.maplr.test.sugarshack.mapleordersapi.mapper;

import java.util.List;

public interface EntityToDtoMapper<E, O> {

    O entityToDto(E entity);

    default List<O> entitiesToDtos(List<E> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(this::entityToDto).toList();
    }

}
