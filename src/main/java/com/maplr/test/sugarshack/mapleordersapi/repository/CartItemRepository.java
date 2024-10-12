package com.maplr.test.sugarshack.mapleordersapi.repository;

import com.maplr.test.sugarshack.mapleordersapi.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    List<CartItemEntity> findAllByCartEntityIdOrderByProductEntityName(Long id);

    void deleteByCartEntityIdAndProductEntityId(Long cartId, Long productId);

    CartItemEntity findByCartEntityIdAndProductEntityId(Long cartId, Long productId);

    @Modifying
    @Query(value = "update cart_item set quantity = :newQty, total_price = :newTotal where cart_id = :cartId and product_id = :productId", nativeQuery = true)
    void updateProductQuantiyById(
            @Param("newQty") Integer newQty,
            @Param("newTotal") Float newTotal,
            @Param("cartId") Long cartId,
            @Param("productId") Long productId
    );
}
