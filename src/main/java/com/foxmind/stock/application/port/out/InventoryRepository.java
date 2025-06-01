package com.foxmind.stock.application.port.out;

import com.foxmind.stock.application.exception.InventoryTransactionErrorException;
import com.foxmind.stock.domain.entity.InventoryEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InventoryRepository {
    Mono<InventoryEntity> save(InventoryEntity entity) throws InventoryTransactionErrorException;
    Flux<InventoryEntity> list(int page, int size) throws InventoryTransactionErrorException;
    Mono<InventoryEntity> byId(String id) throws InventoryTransactionErrorException;
    Mono<InventoryEntity> byName(String name) throws InventoryTransactionErrorException;
    Mono<Void> deleteById(String id) throws InventoryTransactionErrorException;
}