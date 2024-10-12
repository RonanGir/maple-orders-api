package com.maplr.test.sugarshack.mapleordersapi.mapper;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.order.OrderItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.OrderItemEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(uses = {ProductMapper.class})
public interface OrderItemMapper extends BaseMapper<OrderItemEntity, OrderItemDto> {

    @Mapping(source = "productEntity", target = "productDto", qualifiedByName = "productEntityToDto")
    @Mapping(source = "orderEntity.id", target = "orderId")
    @Mapping(source = "customerEntity.id", target = "customerId")
    OrderItemDto entityToDto(OrderItemEntity entity);

    @Mapping(target = "customerEntity", ignore = true)
    @Mapping(target = "productEntity", ignore = true)
    @Mapping(target = "orderEntity", ignore = true)
    @Mapping(target = "status", ignore = true)
    OrderItemEntity dtoToEntity(OrderItemDto dto);

    @Override
    @Named("orderItemDtosToEntities")
    default List<OrderItemDto> entitiesToDtos(Set<OrderItemEntity> entities) {
        return BaseMapper.super.entitiesToDtos(entities);
    }

    @Override
    @Named("orderItemEntitiesToDtos")
    default Set<OrderItemEntity> dtosToEntities(List<OrderItemDto> entities) {
        return BaseMapper.super.dtosToEntities(entities);
    }

    @Named("cartItemToOderDto")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "quantity", ignore = true)
    @Mapping(target = "productDto", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    OrderItemDto cartItemToOderDto(CartItemDto dto);

    @Named("cartItemsToOderItemDtos")
    @IterableMapping(qualifiedByName = "cartItemToOderDto")
    List<OrderItemDto> cartItemsToOderItemDtos(List<CartItemDto> dtos);

    @Named("cartItemToOrderEntity")
    @Mapping(target = "orderEntity", ignore = true)
    @Mapping(target = "id", ignore = true)
    OrderItemEntity cartItemToOrderEntity(CartItemEntity entity);

    @Named("cartItemsToOderItemEntities")
    @IterableMapping(qualifiedByName = "cartItemToOrderEntity")
    Set<OrderItemEntity> cartItemsToOderItemEntities(List<CartItemEntity> entities);

}
