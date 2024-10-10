package com.maplr.test.sugarshack.mapleordersapi.mapper;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.CatalogueItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper extends BaseMapper<ProductEntity, CatalogueItemDto> {

    @Mapping(target = "description", ignore = true)
    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductEntity dtoToEntity(CatalogueItemDto dto);

}
