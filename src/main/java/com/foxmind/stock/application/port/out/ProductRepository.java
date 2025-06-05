package com.foxmind.stock.application.port.out;

import com.foxmind.stock.application.exception.ProductTransactionalErrorException;
import com.foxmind.stock.domain.entity.ProductEntity;

import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<ProductEntity> save(ProductEntity entity) throws ProductTransactionalErrorException; 
}
