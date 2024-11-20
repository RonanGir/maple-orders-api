package com.maplr.test.sugarshack.mapleordersapi.cart.item;

import com.maplr.test.sugarshack.mapleordersapi.cart.CartEntity;
import com.maplr.test.sugarshack.mapleordersapi.common.TransactionEntity;
import com.maplr.test.sugarshack.mapleordersapi.customer.CustomerEntity;
import com.maplr.test.sugarshack.mapleordersapi.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Data
@Table(name = "cart_item")
@EqualsAndHashCode(callSuper = false)
public class CartItemEntity extends TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_seq_gen")
    @SequenceGenerator(name = "cart_item_seq_gen", sequenceName = "cart_item_seq", allocationSize = 1)
    private Long id;

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
        super(customerEntity, quantity, totalPrice);
        this.productEntity = productEntity;
        this.cartEntity = cartEntity;
    }


}