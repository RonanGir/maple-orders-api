package com.maplr.test.sugarshack.mapleordersapi.exception;

import com.maplr.test.sugarshack.mapleordersapi.exception.dto.MaplOrdersApiException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MaplOrdersApiException> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("EntityNotFoundException: {}", ex.getMessage());
        MaplOrdersApiException appException = MaplOrdersApiException.builder()
                                                                    .status(HttpStatus.BAD_REQUEST.value())
                                                                    .technicalMessage(ex.getMessage())
                                                                    .userMessage("La demande envoyée est incorrecte.")
                                                                    .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(appException);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<MaplOrdersApiException> handleGlobalException(Exception ex) {
        log.error("Exception: {}", ex.getMessage());
        MaplOrdersApiException appException = MaplOrdersApiException.builder()
                                                                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                                                    .technicalMessage(ex.getMessage())
                                                                    .userMessage("Oh non, ça a mal viré !")
                                                                    .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(appException);
    }
}
