package com.maplr.test.sugarshack.mapleordersapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

import java.util.Set;

@Entity
@Immutable
@Data
@Table(name = "purchase_order")
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "purchase_order_seq", sequenceName = "purchase_order_seq")
public class OrderEntity extends TransactionEntity {

    @OneToMany(mappedBy = "orderEntity")
    private Set<OrderItemEntity> orderItemEntities;

}
