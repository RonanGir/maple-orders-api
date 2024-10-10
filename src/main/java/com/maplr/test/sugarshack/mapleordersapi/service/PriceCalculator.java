package com.maplr.test.sugarshack.mapleordersapi.service;

import org.springframework.stereotype.Service;

@Service
public class PriceCalculator {

    public Float calcPrice(Integer quantity, Double price) {
        return quantity * price.floatValue();
    }

}
