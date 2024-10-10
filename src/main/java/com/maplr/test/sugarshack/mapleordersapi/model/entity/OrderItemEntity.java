package com.maplr.test.sugarshack.mapleordersapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "purchase_order_item")
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "purchase_order_item_seq", sequenceName = "purchase_order_item_seq")
public class OrderItemEntity extends TransactionEntity {

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity;



}