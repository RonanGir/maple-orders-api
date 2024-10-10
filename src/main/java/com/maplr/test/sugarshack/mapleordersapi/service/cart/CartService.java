package com.maplr.test.sugarshack.mapleordersapi.service.cart;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartModificationDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartEntity;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CustomerEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.CartRepository;
import com.maplr.test.sugarshack.mapleordersapi.repository.ProductRepository;
import com.maplr.test.sugarshack.mapleordersapi.service.CustomerService;
import com.maplr.test.sugarshack.mapleordersapi.service.PriceCalculator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartService {

    private CartRepository repository;
    private ProductRepository productRepository;
    private PriceCalculator priceCalculator;
    private CustomerService customerService;

    @Autowired
    public CartService(
            CartRepository repository,
            ProductRepository productRepository,
            PriceCalculator priceCalculator,
            CustomerService customerService
    ) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.priceCalculator = priceCalculator;
        this.customerService = customerService;
    }

    @Transactional
    public void addToCart(CartModificationDto cartModificationDto) {
        CustomerEntity customer = customerService.findById(cartModificationDto.userId());

        if (customer != null) {
            Optional<CartEntity> optionalCartEntity = repository.findById(cartModificationDto.cartId());

            optionalCartEntity.ifPresent(cartEntity -> {
                Set<CartItemEntity> otherItems = cartEntity.getCartItemEntities();

                productRepository.findById(cartModificationDto.productId()).ifPresent(productEntity -> {
                    Float totalPrice = priceCalculator.calcPrice(cartModificationDto.qty(), productEntity.getPrice());
                    CartItemEntity newItem = new CartItemEntity(productEntity, cartEntity, customer, cartModificationDto.qty(), totalPrice.floatValue());
                    otherItems.add(newItem);
                });

                // Sauvegarder les modifications dans le panier
                repository.save(cartEntity);
            });
        }

    }

    public void deleteFromCart(CartModificationDto cartModificationDto) {
        Optional<CartEntity> entity = repository.findById(cartModificationDto.cartId()).stream().map(cartEntity -> {
            Set<CartItemEntity> otherItems = cartEntity.getCartItemEntities().stream().filter(cartItemEntity -> cartModificationDto.productId() != cartItemEntity.getProductEntity().getId()).collect(Collectors.toSet());
            cartEntity.setCartItemEntities(otherItems);
            return cartEntity;
        }).findFirst();
        entity.ifPresent(repository::save);
    }
}
