package com.maplr.test.sugarshack.mapleordersapi.service.cart;

import com.maplr.test.sugarshack.mapleordersapi.mapper.CartItemMapper;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartItemDto;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartModificationDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.ProductEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.CartItemRepository;
import com.maplr.test.sugarshack.mapleordersapi.service.CrudService;
import com.maplr.test.sugarshack.mapleordersapi.service.PriceCalculatorService;
import com.maplr.test.sugarshack.mapleordersapi.service.StockValidatorService;
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
    private final StockValidatorService stockValidatorService;

    @Autowired
    public CartItemService(
            CartItemRepository repository,
            CartItemMapper mapper,
            PriceCalculatorService priceCalculator,
            ProductService productService,
            StockValidatorService stockValidatorService
    ) {
        super(repository);
        this.repository = repository;
        this.mapper = mapper;
        this.priceCalculator = priceCalculator;
        this.productService = productService;
        this.stockValidatorService = stockValidatorService;
    }

    public List<CartItemDto> findAllByCartId(Long cartId) {
        return mapper.entitiesToDtos(repository.findAllByCartEntityIdOrderByProductEntityName(cartId));
    }

    @Transactional
    public void changeQuantity(CartModificationDto cartModificationDto) {
        ProductEntity product = productService.findById(cartModificationDto.productId());

        // Validate availability
        if (stockValidatorService.canOrder(product, cartModificationDto)) {
            Float newTotalPrice = priceCalculator.calcPrice(cartModificationDto.qty(), product.getPrice());
            repository.updateProductQuantiyById(cartModificationDto.qty(), newTotalPrice, cartModificationDto.cartId(), cartModificationDto.productId());
        }

    }
}
