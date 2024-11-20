package com.maplr.test.sugarshack.mapleordersapi.order.item;

import com.maplr.test.sugarshack.mapleordersapi.common.TransactionEntity;
import com.maplr.test.sugarshack.mapleordersapi.order.OrderEntity;
import com.maplr.test.sugarshack.mapleordersapi.product.ProductEntity;
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