package com.maplr.test.sugarshack.mapleordersapi.service.cart;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartModificationDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartEntity;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CustomerEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.CartItemRepository;
import com.maplr.test.sugarshack.mapleordersapi.repository.CartRepository;
import com.maplr.test.sugarshack.mapleordersapi.repository.ProductRepository;
import com.maplr.test.sugarshack.mapleordersapi.service.CustomerService;
import com.maplr.test.sugarshack.mapleordersapi.service.PriceCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.maplr.test.sugarshack.mapleordersapi.testconfig.TestConstant.getSampleProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartItemRepository itemRepository;

    @Mock
    private PriceCalculatorService priceCalculatorService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemService itemService;

    @Mock
    private CustomerService customerService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    @Captor
    ArgumentCaptor<CartModificationDto> cartModificationDtoCaptor;

    @Test
    void should_updateQuantityWhen_addToCart_AnPresentProduct() {
        // given
        CartItemEntity anExistingItem = new CartItemEntity();
        anExistingItem.setQuantity(1);

        when(itemRepository.findByCartEntityIdAndProductEntityId(anyLong(), anyLong()))
                .thenReturn(anExistingItem);

        Mockito.doNothing().when(itemService).changeQuantity(cartModificationDtoCaptor.capture());


        CartModificationDto cartModificationDto = CartModificationDto.builder()
                                                                     .cartId(1L)
                                                                     .productId(1L)
                                                                     .qty(1)
                                                                     .build();
        // when
        cartService.addToCart(cartModificationDto);

        // then
        assertEquals(2, cartModificationDtoCaptor.getValue().qty());
    }

    @Test
    void should_addToCart_WhenProductIsNotPresentInCart() {
        // given

        when(itemRepository.findByCartEntityIdAndProductEntityId(anyLong(), anyLong())).thenReturn(null);

        when(itemRepository.save(any(CartItemEntity.class))).thenReturn(new CartItemEntity());
        when(customerService.findById(anyLong())).thenReturn(new CustomerEntity());
        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(new CartEntity()));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(getSampleProduct()));
        when(priceCalculatorService.calcPrice(anyInt(), anyDouble())).thenReturn(3f);

        CartModificationDto cartModificationDto = CartModificationDto.builder()
                                                                     .cartId(1L)
                                                                     .productId(1L)
                                                                     .userId(1L)
                                                                     .qty(1)
                                                                     .build();
        // when
        cartService.addToCart(cartModificationDto);

        verify(itemRepository, times(1)).save(any(CartItemEntity.class));

    }
    
}