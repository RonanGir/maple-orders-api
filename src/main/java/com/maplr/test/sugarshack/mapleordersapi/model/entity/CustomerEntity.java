package com.maplr.test.sugarshack.mapleordersapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "customer")
@EqualsAndHashCode(callSuper = false)
public class CustomerEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

}
