package com.foxmind.stock.application.port.in;

import com.foxmind.stock.application.exception.InventoryInternalErrorException;
import com.foxmind.stock.domain.dto.response.InventoryResponse;

import reactor.core.publisher.Flux;

public interface InventoryQuery {
    Flux<InventoryResponse> listAll(int page, int size) throws InventoryInternalErrorException;
}