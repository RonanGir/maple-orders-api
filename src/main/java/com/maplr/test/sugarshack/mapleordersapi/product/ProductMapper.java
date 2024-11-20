package com.maplr.test.sugarshack.mapleordersapi.product;

import com.maplr.test.sugarshack.mapleordersapi.common.BaseMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<ProductEntity, ProductDto> {

    @Named("productEntityToDto")
    ProductDto entityToDto(ProductEntity entity);

    @Named("entityToCatalogueItemDto")
    CatalogueItemDto entityToCatalogueItemDto(ProductEntity entity);

    @IterableMapping(qualifiedByName = "entityToCatalogueItemDto")
    List<CatalogueItemDto> entitiesToCatalogueItemDtos(Collection<ProductEntity> entities);

}
