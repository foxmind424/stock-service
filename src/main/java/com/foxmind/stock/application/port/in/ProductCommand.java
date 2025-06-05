package com.foxmind.stock.application.port.in;

import com.foxmind.stock.application.exception.ProductInternalErrorException;
import com.foxmind.stock.domain.dto.request.ProductRequest;
import com.foxmind.stock.domain.dto.response.ProductResponse;

import reactor.core.publisher.Mono;

public interface ProductCommand {
    Mono<ProductResponse> create(ProductRequest request) throws ProductInternalErrorException;
}
