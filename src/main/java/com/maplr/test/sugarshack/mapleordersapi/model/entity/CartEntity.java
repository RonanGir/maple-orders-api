package com.maplr.test.sugarshack.mapleordersapi.model.entity;

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
