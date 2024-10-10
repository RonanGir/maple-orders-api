package com.maplr.test.sugarshack.mapleordersapi.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface BaseMapper<E, O> {

    O entityToDto(E entity);

    E dtoToEntity(O dto);

    default Set<E> dtosToEntities(List<O> entities) {
        if (entities == null) {
            return Set.of();
        }
        return entities.stream().map(this::dtoToEntity).collect(Collectors.toSet());
    }

    default List<O> entitiesToDtos(List<E> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(this::entityToDto).toList();
    }

}
