package com.maplr.test.sugarshack.mapleordersapi.mapper;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.product.CatalogueItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.product.ProductDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.ProductEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<ProductEntity, ProductDto> {

    @Named("productEntityToDto")
    ProductDto entityToDto(ProductEntity entity);

    @Named("entityToCatalogueItemDto")
    CatalogueItemDto entityToCatalogueItemDto(ProductEntity entity);

    @IterableMapping(qualifiedByName = "entityToCatalogueItemDto")
    List<CatalogueItemDto> entitiesToCatalogueItemDtos(List<ProductEntity> entities);

}
