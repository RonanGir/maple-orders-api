package com.maplr.test.sugarshack.mapleordersapi.product;

import com.maplr.test.sugarshack.mapleordersapi.common.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductDetailMapper extends BaseMapper<ProductEntity, MapleSyrupDto> {

    @Mapping(target = "maxQty", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductEntity dtoToEntity(MapleSyrupDto dto);

}
