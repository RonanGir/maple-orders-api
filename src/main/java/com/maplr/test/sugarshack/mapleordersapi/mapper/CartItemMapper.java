package com.maplr.test.sugarshack.mapleordersapi.mapper;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartLineDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItemEntity, CartLineDto> {

    @Mapping(source = "dto.productId", target = "id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "quantity", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "customerEntity", ignore = true)
    @Mapping(target = "productEntity", ignore = true)
    @Mapping(target = "cartEntity", ignore = true)
    @Mapping(target = "status", ignore = true)
    CartItemEntity dtoToEntity(CartLineDto dto);

    @Mapping(source = "entity.productEntity.image", target = "image")
    @Mapping(source = "entity.productEntity.name", target = "name")
    @Mapping(source = "entity.productEntity.price", target = "price")
    @Mapping(source = "entity.productEntity.id", target = "productId")
    @Mapping(source = "entity.quantity", target = "qty")
    CartLineDto entityToDto(CartItemEntity entity);

}
