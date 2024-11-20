package com.maplr.test.sugarshack.mapleordersapi.order;

import com.maplr.test.sugarshack.mapleordersapi.common.TransactionEntity;
import com.maplr.test.sugarshack.mapleordersapi.customer.CustomerEntity;
import com.maplr.test.sugarshack.mapleordersapi.order.item.OrderItemEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

import java.util.Set;

@Entity
@Immutable
@Data
@Table(name = "purchase_order")
@EqualsAndHashCode(callSuper = false)
public class OrderEntity extends TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_order_seq_gen")
    @SequenceGenerator(name = "purchase_order_seq_gen", sequenceName = "purchase_order_seq", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy = "orderEntity")
    private Set<OrderItemEntity> orderItemEntities;

    public OrderEntity() {
    }

    public OrderEntity(CustomerEntity customerEntity, Integer quantity, Float totalPrice, Set<OrderItemEntity> orderItemEntities) {
        super(customerEntity, quantity, totalPrice);
        this.orderItemEntities = orderItemEntities;
    }
}
