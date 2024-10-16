package com.maplr.test.sugarshack.mapleordersapi.service;

import com.maplr.test.sugarshack.mapleordersapi.exception.dto.OutOfStockException;
import com.maplr.test.sugarshack.mapleordersapi.model.dto.cart.CartModificationDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class StockValidatorService {

    public boolean canOrder(@NotNull ProductEntity productEntity, @NotNull CartModificationDto cartModificationDto) {
        int updatedStock = productEntity.getStock() - cartModificationDto.qty();

        if (updatedStock <= 0) {
            String userMessage = productEntity.getStock() == 0 ?
                    "Désolé, le produit est en rupture de stock, nous en préparons d'autres tout bientôt"
                    : "Désolé, Il nous reste que %s %s".formatted(productEntity.getStock(), productEntity.getName());

            throw new OutOfStockException(
                    "UserId = %s tried to add productId = %s [stock=%s]".formatted(cartModificationDto.userId(), productEntity.getId(), productEntity.getStock()),
                    userMessage
            );
        }

        return true;
    }

}
