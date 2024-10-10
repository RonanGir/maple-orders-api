package com.maplr.test.sugarshack.mapleordersapi.service;

import com.maplr.test.sugarshack.mapleordersapi.mapper.ProductDetailMapper;
import com.maplr.test.sugarshack.mapleordersapi.mapper.ProductMapper;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.CatalogueItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.MapleSyrupDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.ProductEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends CrudService<CatalogueItemDto, Long, ProductEntity, ProductMapper, ProductRepository> {

    private ProductMapper mapper;
    private ProductDetailMapper mapperDetail;
    private ProductRepository repository;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper,
            ProductDetailMapper mapperDetail
    ) {
        super(productRepository, productMapper);
        this.mapper = productMapper;
        this.mapperDetail = mapperDetail;
        this.repository = productRepository;
    }

    public MapleSyrupDto getProductInfo(Long id) {
        return repository.findById(id).map(mapperDetail::entityToDto).orElse(null);
    }

}
