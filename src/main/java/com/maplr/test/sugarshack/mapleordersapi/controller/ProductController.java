package com.maplr.test.sugarshack.mapleordersapi.controller;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.CatalogueItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.MapleSyrupDto;
import com.maplr.test.sugarshack.mapleordersapi.service.product.ProductDetailService;
import com.maplr.test.sugarshack.mapleordersapi.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/api")
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
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<MapleSyrupDto> getProductDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productDetailService.getProductInfo(id));
    }

}
