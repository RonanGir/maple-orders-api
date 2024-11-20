package com.maplr.test.sugarshack.mapleordersapi.cart;

import com.maplr.test.sugarshack.mapleordersapi.cart.item.CartItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.cart.item.CartItemRepository;
import com.maplr.test.sugarshack.mapleordersapi.cart.item.CartItemService;
import com.maplr.test.sugarshack.mapleordersapi.common.CrudService;
import com.maplr.test.sugarshack.mapleordersapi.customer.CustomerEntity;
import com.maplr.test.sugarshack.mapleordersapi.customer.CustomerService;
import com.maplr.test.sugarshack.mapleordersapi.inventory.InventoryValidatorService;
import com.maplr.test.sugarshack.mapleordersapi.pricing.PriceCalculatorService;
import com.maplr.test.sugarshack.mapleordersapi.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CartService extends CrudService<CartEntity, Long> {

    private final CartRepository repository;
    private final ProductRepository productRepository;
    private final PriceCalculatorService priceCalculator;
    private final CustomerService customerService;
    private final CartItemService cartItemService;
    private final CartItemRepository itemRepository;
    private final InventoryValidatorService inventoryValidatorService;


    @Autowired
    public CartService(
            CartRepository repository,
            CartItemRepository itemRepository,
            ProductRepository productRepository,
            PriceCalculatorService priceCalculator,
            CustomerService customerService,
            CartItemService cartItemService,
            InventoryValidatorService inventoryValidatorService
    ) {
        super(repository);
        this.repository = repository;
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
        this.priceCalculator = priceCalculator;
        this.customerService = customerService;
        this.cartItemService = cartItemService;
        this.inventoryValidatorService = inventoryValidatorService;
    }

    public void addToCart(CartModificationDto cartModificationDto) {
        log.info("[addToCart] begin with productId={} and cartId={}", cartModificationDto.productId(), cartModificationDto.cartId());
        CartItemEntity cartItemEntity = itemRepository.findByCartEntityIdAndProductEntityId(cartModificationDto.cartId(), cartModificationDto.productId());
        if (cartItemEntity != null) {
            CartModificationDto updatedCartChange = CartModificationDto.builder()
                                                                       .qty(cartItemEntity.getQuantity() + cartModificationDto.qty())
                                                                       .productId(cartModificationDto.productId())
                                                                       .cartId(cartModificationDto.cartId())
                                                                       .build();
            cartItemService.changeQuantity(updatedCartChange);
        } else {

            CustomerEntity customer = customerService.findById(cartModificationDto.userId());
            if (customer != null) {
                Optional<CartEntity> optionalCartEntity = repository.findById(cartModificationDto.cartId());
                optionalCartEntity.ifPresent(cartEntity -> {
                    productRepository.findById(cartModificationDto.productId()).ifPresent(productEntity -> {
                        if (inventoryValidatorService.canOrder(productEntity, cartModificationDto)) {
                            Float totalPrice = priceCalculator.calcPrice(cartModificationDto.qty(), productEntity.getPrice());
                            CartItemEntity newItem = new CartItemEntity(productEntity, cartEntity, customer, cartModificationDto.qty(), totalPrice);
                            itemRepository.save(newItem);
                        }
                    });
                });
            }
        }


    }

    @Transactional
    public void deleteFromCart(CartModificationDto cartModificationDto) {
        itemRepository.deleteByCartEntityIdAndProductEntityId(cartModificationDto.cartId(), cartModificationDto.productId());
    }


}
