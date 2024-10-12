package com.maplr.test.sugarshack.mapleordersapi.mapper;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.product.MapleSyrupDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductDetailMapper extends BaseMapper<ProductEntity, MapleSyrupDto> {

    @Mapping(target = "maxQty", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductEntity dtoToEntity(MapleSyrupDto dto);

}
