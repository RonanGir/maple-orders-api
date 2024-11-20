package com.maplr.test.sugarshack.mapleordersapi.order.item;

import com.maplr.test.sugarshack.mapleordersapi.cart.item.CartItemDto;
import com.maplr.test.sugarshack.mapleordersapi.cart.item.CartItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.common.BaseMapper;
import com.maplr.test.sugarshack.mapleordersapi.product.ProductMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
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
    default List<OrderItemDto> entitiesToDtos(Collection<OrderItemEntity> entities) {
        return BaseMapper.super.entitiesToDtos(entities);
    }

    @Override
    @Named("orderItemEntitiesToDtos")
    default Set<OrderItemEntity> dtosToEntities(Collection<OrderItemDto> entities) {
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

    @Named("cartItemToOrderEntity")
    @Mapping(target = "orderEntity", ignore = true)
    @Mapping(target = "id", ignore = true)
    OrderItemEntity cartItemToOrderEntity(CartItemEntity entity);

    @Named("cartItemsToOderItemEntities")
    @IterableMapping(qualifiedByName = "cartItemToOrderEntity")
    Set<OrderItemEntity> cartItemsToOderItemEntities(Collection<CartItemEntity> entities);

}
