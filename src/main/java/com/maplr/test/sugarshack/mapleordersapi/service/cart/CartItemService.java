package com.maplr.test.sugarshack.mapleordersapi.service.cart;

import com.maplr.test.sugarshack.mapleordersapi.mapper.CartItemMapper;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartLineDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.CartItemRepository;
import com.maplr.test.sugarshack.mapleordersapi.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService extends CrudService<CartLineDto, Long, CartItemEntity, CartItemMapper, CartItemRepository> {

    private CartItemRepository repository;
    private CartItemMapper mapper;

    @Autowired
    public CartItemService(CartItemRepository repository, CartItemMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CartLineDto> findAllByCartId(Long cartId) {
        return mapper.entitiesToDtos(repository.findAllByCartEntityId(cartId));
    }
}
