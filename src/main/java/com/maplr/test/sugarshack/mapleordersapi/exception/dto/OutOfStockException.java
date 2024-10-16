package com.maplr.test.sugarshack.mapleordersapi.exception.dto;

import lombok.Getter;

@Getter
public class OutOfStockException extends RuntimeException {
    String userMessage;

    public OutOfStockException(String technicalMessage, String userMessage) {
        super(technicalMessage);
        this.userMessage = userMessage;
    }
}
