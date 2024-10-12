package com.maplr.test.sugarshack.mapleordersapi.mapper;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = {CartItemMapper.class, CustomerMapper.class})
public interface CartMapper extends BaseMapper<CartEntity, CartDto> {

    @Named("cartDtoToEntity")
    @Mapping(source = "customerDto", target = "customerEntity", qualifiedByName = "customerDtoToEntity")
    @Mapping(source = "items", target = "cartItemEntities", qualifiedByName = "cartItemDtosToEntities")
    CartEntity dtoToEntity(CartDto dto);

    @Named("cartEntityToDto")
    @Mapping(source = "customerEntity", target = "customerDto", qualifiedByName = "customerEntityToDto")
    @Mapping(source = "cartItemEntities", target = "items", qualifiedByName = "cartItemEntitiesToDtos")
    CartDto entityToDto(CartEntity entity);
}
