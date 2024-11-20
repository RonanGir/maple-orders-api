package com.maplr.test.sugarshack.mapleordersapi.cart.item;

import com.maplr.test.sugarshack.mapleordersapi.cart.CartModificationDto;
import com.maplr.test.sugarshack.mapleordersapi.common.CrudService;
import com.maplr.test.sugarshack.mapleordersapi.inventory.InventoryValidatorService;
import com.maplr.test.sugarshack.mapleordersapi.pricing.PriceCalculatorService;
import com.maplr.test.sugarshack.mapleordersapi.product.ProductEntity;
import com.maplr.test.sugarshack.mapleordersapi.product.ProductService;
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
    private final InventoryValidatorService inventoryValidatorService;

    @Autowired
    public CartItemService(
            CartItemRepository repository,
            CartItemMapper mapper,
            PriceCalculatorService priceCalculator,
            ProductService productService,
            InventoryValidatorService inventoryValidatorService
    ) {
        super(repository);
        this.repository = repository;
        this.mapper = mapper;
        this.priceCalculator = priceCalculator;
        this.productService = productService;
        this.inventoryValidatorService = inventoryValidatorService;
    }

    public List<CartItemDto> findAllByCartId(Long cartId) {
        return mapper.entitiesToDtos(repository.findAllByCartEntityIdOrderByProductEntityName(cartId));
    }

    @Transactional
    public void changeQuantity(CartModificationDto cartModificationDto) {
        ProductEntity product = productService.findById(cartModificationDto.productId());

        // Validate availability
        if (inventoryValidatorService.canOrder(product, cartModificationDto)) {
            Float newTotalPrice = priceCalculator.calcPrice(cartModificationDto.qty(), product.getPrice());
            repository.updateProductQuantiyById(cartModificationDto.qty(), newTotalPrice, cartModificationDto.cartId(), cartModificationDto.productId());
        }

    }
}
