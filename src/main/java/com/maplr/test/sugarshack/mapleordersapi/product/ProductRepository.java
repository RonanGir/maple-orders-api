package com.maplr.test.sugarshack.mapleordersapi.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Modifying
    @Query(value = "update product set max_quantity = :newQty where id = :productId", nativeQuery = true)
    void updateProductQuantiyById(
            @Param("newQty") Integer newQty,
            @Param("productId") Long productId
    );
}
