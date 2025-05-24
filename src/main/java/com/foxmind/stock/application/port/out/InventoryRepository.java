package com.foxmind.stock.application.port.out;

import com.foxmind.stock.application.exception.InventoryTransactionErrorException;
import com.foxmind.stock.domain.entity.InventoryEntity;

public interface InventoryRepository {
    InventoryEntity save(InventoryEntity entity) throws InventoryTransactionErrorException;
}