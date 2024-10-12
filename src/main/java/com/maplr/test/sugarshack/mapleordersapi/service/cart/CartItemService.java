package com.maplr.test.sugarshack.mapleordersapi.service.cart;

import com.maplr.test.sugarshack.mapleordersapi.mapper.CartItemMapper;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartModificationDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.ProductEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.CartItemRepository;
import com.maplr.test.sugarshack.mapleordersapi.service.CrudService;
import com.maplr.test.sugarshack.mapleordersapi.service.PriceCalculatorService;
import com.maplr.test.sugarshack.mapleordersapi.service.product.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService extends CrudService<CartItemEntity, Long> {

    private final CartItemRepository repository;
    private final CartItemMapper mapper;
    private final PriceCalculatorService priceCalculator;
    private final ProductService productService;

    @Autowired
    public CartItemService(
            CartItemRepository repository,
            CartItemMapper mapper,
            PriceCalculatorService priceCalculator,
            ProductService productService
    ) {
        super(repository);
        this.repository = repository;
        this.mapper = mapper;
        this.priceCalculator = priceCalculator;
        this.productService = productService;
    }

    public List<CartItemDto> findAllByCartId(Long cartId) {
        return mapper.entitiesToDtos(repository.findAllByCartEntityIdOrderByProductEntityName(cartId));
    }

    @Transactional
    public void changeQuantity(CartModificationDto cartModificationDto) {
        // calculate price
        ProductEntity product = productService.findById(cartModificationDto.productId());
        Float newTotalPrice = priceCalculator.calcPrice(cartModificationDto.qty(), product.getPrice());
        repository.updateProductQuantiyById(cartModificationDto.qty(), newTotalPrice, cartModificationDto.cartId(), cartModificationDto.productId());
    }
}
