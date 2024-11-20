package com.maplr.test.sugarshack.mapleordersapi.product;

import com.maplr.test.sugarshack.mapleordersapi.common.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService extends CrudService<ProductEntity, Long> {

    private ProductDetailMapper mapperDetail;

    @Autowired
    public ProductDetailService(ProductRepository productRepository, ProductDetailMapper mapperDetail) {
        super(productRepository);
        this.mapperDetail = mapperDetail;
    }
    
    public MapleSyrupDto getProductInfo(Long id) {
        return mapperDetail.entityToDto(super.findById(id));
    }

}
