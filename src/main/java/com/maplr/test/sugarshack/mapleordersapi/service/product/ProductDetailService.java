package com.maplr.test.sugarshack.mapleordersapi.service.product;

import com.maplr.test.sugarshack.mapleordersapi.mapper.ProductDetailMapper;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.MapleSyrupDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.ProductEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.ProductRepository;
import com.maplr.test.sugarshack.mapleordersapi.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService extends CrudService<MapleSyrupDto, Long, ProductEntity, ProductDetailMapper, ProductRepository> {

    @Autowired
    public ProductDetailService(ProductRepository productRepository,ProductDetailMapper mapperDetail) {
        super(productRepository, mapperDetail);
    }

    public MapleSyrupDto getProductInfo(Long id) {
        return super.findById(id);
    }

}
