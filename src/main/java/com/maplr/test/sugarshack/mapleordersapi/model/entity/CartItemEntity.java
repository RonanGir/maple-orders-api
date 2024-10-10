package com.maplr.test.sugarshack.mapleordersapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Data
@Table(name = "cart_item")
@EqualsAndHashCode(callSuper = false)
public class CartItemEntity extends TransactionEntity {

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cartEntity;

    public CartItemEntity() {
        super();
    }
    public CartItemEntity(
            ProductEntity productEntity,
            CartEntity cartEntity,
            CustomerEntity customerEntity,
            Integer quantity,
            Float totalPrice
    ) {
        super(customerEntity, quantity,totalPrice);
        this.productEntity = productEntity;
        this.cartEntity = cartEntity;
    }


}