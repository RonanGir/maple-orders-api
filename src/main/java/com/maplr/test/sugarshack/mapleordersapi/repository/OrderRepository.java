package com.maplr.test.sugarshack.mapleordersapi.repository;

import com.maplr.test.sugarshack.mapleordersapi.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> getOrderEntitiesByCustomerEntityId(Long id);
}
