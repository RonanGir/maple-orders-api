package com.maplr.test.sugarshack.mapleordersapi.model.entity;

import com.maplr.test.sugarshack.mapleordersapi.model.enums.MapleSyrupTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Data
@Table(name = "product")
@EqualsAndHashCode(callSuper = false)
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    @SequenceGenerator(name = "product_seq_gen", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    @Column(name = "img")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "max_quantity")
    private Integer maxQty;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MapleSyrupTypeEnum type;

    public ProductEntity() {
        super(null);
    }
}
