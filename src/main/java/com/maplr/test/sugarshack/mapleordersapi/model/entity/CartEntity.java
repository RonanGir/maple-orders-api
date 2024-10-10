package com.maplr.test.sugarshack.mapleordersapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@Table(name = "cart")
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq")
public class CartEntity extends TransactionEntity {

    @OneToMany(mappedBy = "cartEntity")
    private Set<CartItemEntity> cartItemEntities;

}
