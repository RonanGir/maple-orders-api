package com.maplr.test.sugarshack.mapleordersapi.cart;

import com.maplr.test.sugarshack.mapleordersapi.cart.item.CartItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.common.TransactionEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@Table(name = "cart")
@EqualsAndHashCode(callSuper = false)
public class CartEntity extends TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq_gen")
    @SequenceGenerator(name = "cart_seq_gen", sequenceName = "cart_seq", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy = "cartEntity")
    private Set<CartItemEntity> cartItemEntities;

}
