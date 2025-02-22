package com.maplr.test.sugarshack.mapleordersapi.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MaplOrdersApiException {
    Integer status;
    String technicalMessage;
    String userMessage;
}
