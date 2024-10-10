package com.maplr.test.sugarshack.mapleordersapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    private Long id;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    protected BaseEntity() {
    }
}
