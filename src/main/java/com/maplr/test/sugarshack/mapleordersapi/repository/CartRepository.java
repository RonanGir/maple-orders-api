package com.maplr.test.sugarshack.mapleordersapi.repository;

import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
