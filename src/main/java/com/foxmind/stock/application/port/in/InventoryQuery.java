package com.foxmind.stock.application.port.in;

import com.foxmind.stock.application.exception.InventoryInternalErrorException;
import com.foxmind.stock.application.exception.InventoryNotFoundErrorException;
import com.foxmind.stock.domain.dto.response.InventoryResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InventoryQuery {
    Flux<InventoryResponse> listAll(int page, int size) throws InventoryInternalErrorException;
    Mono<InventoryResponse> findById(String id) throws InventoryNotFoundErrorException, InventoryInternalErrorException;
    Mono<InventoryResponse> findByName(String name) throws InventoryNotFoundErrorException, InventoryInternalErrorException;
}