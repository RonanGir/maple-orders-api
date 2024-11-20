package com.maplr.test.sugarshack.mapleordersapi.cart.item;

import com.maplr.test.sugarshack.mapleordersapi.common.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItemEntity, CartItemDto> {

    @Named("cartItemDtoToEntity")
    @Mapping(source = "dto.productId", target = "id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "quantity", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "customerEntity", ignore = true)
    @Mapping(target = "productEntity", ignore = true)
    @Mapping(target = "cartEntity", ignore = true)
    @Mapping(target = "status", ignore = true)
    CartItemEntity dtoToEntity(CartItemDto dto);

    @Named("cartItemEntityToDto")
    @Mapping(source = "entity.productEntity.image", target = "image")
    @Mapping(source = "entity.productEntity.name", target = "name")
    @Mapping(source = "entity.productEntity.price", target = "price")
    @Mapping(source = "entity.productEntity.id", target = "productId")
    @Mapping(source = "entity.quantity", target = "qty")
    @Mapping(source = "entity.cartEntity.id", target = "cartId")
    CartItemDto entityToDto(CartItemEntity entity);

    @Named("cartItemDtosToEntities")
    default Set<CartItemEntity> dtosToEntities(Collection<CartItemDto> entities) {
        return BaseMapper.super.dtosToEntities(entities);
    }

    @Named("cartItemEntitiesToDtos")
    default List<CartItemDto> entitiesToDtos(Collection<CartItemEntity> entities) {
        return BaseMapper.super.entitiesToDtos(entities);
    }
}
