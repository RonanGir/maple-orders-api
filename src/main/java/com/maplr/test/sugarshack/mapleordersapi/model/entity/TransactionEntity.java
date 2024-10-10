package com.maplr.test.sugarshack.mapleordersapi.model.entity;

import com.maplr.test.sugarshack.mapleordersapi.model.enums.TransactionEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public abstract class TransactionEntity extends BaseEntity {

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_price", nullable = false)
    private Float totalPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customerEntity;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionEnum status;

    protected TransactionEntity() {
        super();
    }

    protected TransactionEntity(CustomerEntity customerEntity, Integer quantity, Float totalPrice) {
        this.customerEntity = customerEntity;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = TransactionEnum.CLOSED;
    }
}
