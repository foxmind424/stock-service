package com.foxmind.stock.application.port.in;

import com.foxmind.stock.application.exception.InventoryInternalErrorException;
import com.foxmind.stock.domain.dto.request.InventoryRequest;
import com.foxmind.stock.domain.dto.response.InventoryResponse;

import reactor.core.publisher.Mono;

public interface InventoryCommand {
    Mono<InventoryResponse> create(InventoryRequest request) throws InventoryInternalErrorException;
}