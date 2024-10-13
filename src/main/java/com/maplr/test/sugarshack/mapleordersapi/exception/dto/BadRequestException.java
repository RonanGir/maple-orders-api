package com.maplr.test.sugarshack.mapleordersapi.exception.dto;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
