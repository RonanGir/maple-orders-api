package com.maplr.test.sugarshack.mapleordersapi.customer;

import com.maplr.test.sugarshack.mapleordersapi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "customer")
@EqualsAndHashCode(callSuper = false)
public class CustomerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    @SequenceGenerator(name = "customer_seq_gen", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    public CustomerEntity() {
        super(null);
    }

}
