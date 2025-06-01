package com.foxmind.stock.application.port.in;

import com.foxmind.stock.application.exception.InventoryInternalErrorException;
import com.foxmind.stock.application.exception.InventoryNotFoundErrorException;
import com.foxmind.stock.domain.dto.request.InventoryRequest;
import com.foxmind.stock.domain.dto.response.InventoryResponse;

import reactor.core.publisher.Mono;

public interface InventoryCommand {
    Mono<InventoryResponse> create(InventoryRequest request) throws InventoryInternalErrorException;
    Mono<InventoryResponse> updateById(InventoryRequest request, String id) throws InventoryNotFoundErrorException, InventoryInternalErrorException;
    Mono<InventoryResponse> updateByName(InventoryRequest request, String name) throws InventoryNotFoundErrorException, InventoryInternalErrorException;
    Mono<InventoryResponse> updatePartialByName(InventoryRequest request, String name) throws InventoryNotFoundErrorException, InventoryInternalErrorException;
    Mono<InventoryResponse> updatePartialById(InventoryRequest request, String id) throws InventoryNotFoundErrorException, InventoryInternalErrorException;
    Mono<Void> deleteById(String id) throws InventoryNotFoundErrorException, InventoryInternalErrorException;
}