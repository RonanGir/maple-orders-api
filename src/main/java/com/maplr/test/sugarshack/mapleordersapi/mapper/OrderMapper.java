package com.maplr.test.sugarshack.mapleordersapi.mapper;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.order.OrderDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(uses = {OrderItemMapper.class, CustomerMapper.class})
public interface OrderMapper extends BaseMapper<OrderEntity, OrderDto> {

    OrderDto entityToDto(OrderEntity entity);

    @Mapping(target = "quantity", ignore = true)
    @Mapping(target = "customerEntity", ignore = true)
    @Mapping(target = "orderItemEntities", ignore = true)
    OrderEntity dtoToEntity(OrderDto dto);

    @Override
    default List<OrderDto> entitiesToDtos(Set<OrderEntity> entities) {
        return BaseMapper.super.entitiesToDtos(entities);
    }
}
