package com.maplr.test.sugarshack.mapleordersapi.service.cart;

import com.maplr.test.sugarshack.mapleordersapi.mapper.CartItemMapper;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.CatalogueItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartLineDto;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartModificationDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.CartItemRepository;
import com.maplr.test.sugarshack.mapleordersapi.service.CrudService;
import com.maplr.test.sugarshack.mapleordersapi.service.PriceCalculator;
import com.maplr.test.sugarshack.mapleordersapi.service.product.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService extends CrudService<CartLineDto, Long, CartItemEntity, CartItemMapper, CartItemRepository> {

    private CartItemRepository repository;
    private CartItemMapper mapper;
    private PriceCalculator priceCalculator;
    private ProductService productService;

    @Autowired
    public CartItemService(
            CartItemRepository repository,
            CartItemMapper mapper,
            PriceCalculator priceCalculator,
            ProductService productService
    ) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.priceCalculator = priceCalculator;
        this.productService = productService;
    }

    public List<CartLineDto> findAllByCartId(Long cartId) {
        return mapper.entitiesToDtos(repository.findAllByCartEntityId(cartId));
    }

    @Transactional
    public void changeQuantity(CartModificationDto cartModificationDto) {
        // calculate price
        CatalogueItemDto catalogueItemDto = productService.findById(cartModificationDto.productId());
        Float newTotalPrice = priceCalculator.calcPrice(cartModificationDto.qty(), catalogueItemDto.price());
        repository.updateProductQuantiyById(cartModificationDto.qty(), newTotalPrice, cartModificationDto.cartId(), cartModificationDto.productId());
    }
}
