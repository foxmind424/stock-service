package com.foxmind.stock.application.port.in;

import com.foxmind.stock.application.exception.CategoryInternalErrorException;
import com.foxmind.stock.application.exception.InventoryNotFoundErrorException;
import com.foxmind.stock.domain.dto.request.CategoryRequest;
import com.foxmind.stock.domain.dto.response.CategoryResponse;

import reactor.core.publisher.Mono;

public interface CategoryCommand {
    Mono<CategoryResponse> create(CategoryRequest request) throws InventoryNotFoundErrorException, CategoryInternalErrorException;
}
