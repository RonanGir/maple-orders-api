package com.maplr.test.sugarshack.mapleordersapi.service;

import com.maplr.test.sugarshack.mapleordersapi.model.entity.TransactionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    public Float calcPrice(Integer quantity, Double price) {
        return quantity * price.floatValue();
    }

    public <T extends TransactionEntity> Float getTotalPrice(List<T> entities) {
        return entities.stream().reduce(0f, (sum, e) -> sum + e.getTotalPrice(), Float::sum);
    }

}
