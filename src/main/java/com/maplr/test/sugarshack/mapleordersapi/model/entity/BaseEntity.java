package com.maplr.test.sugarshack.mapleordersapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    protected BaseEntity(Long id) {
        if (id == null) {
            this.createdAt = ZonedDateTime.now();
        }
        this.updatedAt = ZonedDateTime.now();
    }

}
