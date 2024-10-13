package com.maplr.test.sugarshack.mapleordersapi.service;


import com.maplr.test.sugarshack.mapleordersapi.model.entity.OrderItemEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Only load needed beans
@ExtendWith(SpringExtension.class)
@Import(PriceCalculatorService.class)
class PriceCalculatorServiceTest {

    private PriceCalculatorService priceCalculator = new PriceCalculatorService();

    @Test
    void should_multiplyPriceByQuantity() {
        assertEquals(20, priceCalculator.calcPrice(4, 5d));
    }

    @Test
    void should_sumTotalPrices_whenGiveTransactionEntityObjectList() {
        OrderItemEntity orderItem1 = new OrderItemEntity();
        orderItem1.setTotalPrice(1f);
        OrderItemEntity orderItem2 = new OrderItemEntity();
        orderItem2.setTotalPrice(1f);

        assertEquals(2, priceCalculator.getTotalPrice(List.of(orderItem1, orderItem2)));
    }
}