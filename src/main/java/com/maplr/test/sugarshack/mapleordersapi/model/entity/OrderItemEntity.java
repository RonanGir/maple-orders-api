package com.maplr.test.sugarshack.mapleordersapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "purchase_order_item")
@EqualsAndHashCode(callSuper = false)
public class OrderItemEntity extends TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_order_item_seq_gen")
    @SequenceGenerator(name = "purchase_order_item_seq_gen", sequenceName = "purchase_order_item_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity;


}