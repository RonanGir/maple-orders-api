package com.maplr.test.sugarshack.mapleordersapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@Table(name = "cart")
@EqualsAndHashCode(callSuper = false)
public class CartEntity extends TransactionEntity {

    @OneToMany(mappedBy = "cartEntity")
    private Set<CartItemEntity> cartItemEntities;

}
