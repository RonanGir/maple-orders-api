package com.maplr.test.sugarshack.mapleordersapi.order;

import com.maplr.test.sugarshack.mapleordersapi.common.BaseMapper;
import com.maplr.test.sugarshack.mapleordersapi.customer.CustomerMapper;
import com.maplr.test.sugarshack.mapleordersapi.order.item.OrderItemMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {OrderItemMapper.class, CustomerMapper.class})
public interface OrderMapper extends BaseMapper<OrderEntity, OrderDto> {

    OrderDto entityToDto(OrderEntity entity);

    @Mapping(target = "quantity", ignore = true)
    @Mapping(target = "customerEntity", ignore = true)
    @Mapping(target = "orderItemEntities", ignore = true)
    OrderEntity dtoToEntity(OrderDto dto);

    @Override
    default List<OrderDto> entitiesToDtos(Collection<OrderEntity> entities) {
        return BaseMapper.super.entitiesToDtos(entities);
    }
}
