package com.maplr.test.sugarshack.mapleordersapi.controller;

import com.maplr.test.sugarshack.mapleordersapi.product.CatalogueItemDto;
import com.maplr.test.sugarshack.mapleordersapi.product.MapleSyrupDto;
import com.maplr.test.sugarshack.mapleordersapi.product.ProductDetailService;
import com.maplr.test.sugarshack.mapleordersapi.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${app.api.prefix}/public/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;
    private final ProductDetailService productDetailService;

    @Autowired
    public ProductController(ProductService productService, ProductDetailService productDetailService) {
        this.productService = productService;
        this.productDetailService = productDetailService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<CatalogueItemDto>> getProducts() {
        return ResponseEntity.ok(productService.getProductCatalogue());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<MapleSyrupDto> getProductDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productDetailService.getProductInfo(id));
    }

}
