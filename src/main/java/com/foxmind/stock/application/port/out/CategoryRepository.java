package com.foxmind.stock.application.port.out;

import com.foxmind.stock.application.exception.CategoryTransactionalErrorException;
import com.foxmind.stock.domain.entity.CategoryEntity;

import reactor.core.publisher.Mono;

public interface CategoryRepository {
    Mono<CategoryEntity> save(CategoryEntity entity) throws CategoryTransactionalErrorException;
}
