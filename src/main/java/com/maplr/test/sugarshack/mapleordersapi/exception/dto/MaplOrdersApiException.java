package com.maplr.test.sugarshack.mapleordersapi.exception.dto;

import lombok.Builder;

@Builder
public class MaplOrdersApiException {
    Integer status;
    String technicalMessage;
    String userMessage;
}
