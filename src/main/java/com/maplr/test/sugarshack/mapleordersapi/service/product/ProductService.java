package com.maplr.test.sugarshack.mapleordersapi.service.product;

import com.maplr.test.sugarshack.mapleordersapi.mapper.ProductMapper;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.CatalogueItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.ProductEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.ProductRepository;
import com.maplr.test.sugarshack.mapleordersapi.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends CrudService<CatalogueItemDto, Long, ProductEntity, ProductMapper, ProductRepository> {

    @Autowired
    public ProductService(ProductRepository productRepository,ProductMapper productMapper) {
        super(productRepository, productMapper);
    }

}
