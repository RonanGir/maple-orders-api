package com.maplr.test.sugarshack.mapleordersapi.service.product;

import com.maplr.test.sugarshack.mapleordersapi.mapper.ProductMapper;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.product.CatalogueItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.ProductEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.ProductRepository;
import com.maplr.test.sugarshack.mapleordersapi.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService extends CrudService<ProductEntity, Long> {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        super(productRepository);
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<CatalogueItemDto> getProductCatalogue() {
        return productMapper.entitiesToCatalogueItemDtos(productRepository.findAll());
    }

    public void updateProductStock(Integer boughtQty, Long productId) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(productId);

        optionalProduct.ifPresent(product -> {
            Integer newQty = product.getMaxQty() - boughtQty;
            productRepository.updateProductQuantiyById(newQty, productId);
        });
    }


}
