package com.foxmind.stock.adpater.out.persistence;

import org.springframework.stereotype.Repository;

import com.foxmind.stock.application.exception.ProductTransactionalErrorException;
import com.foxmind.stock.application.port.out.ProductRepository;
import com.foxmind.stock.domain.entity.ProductEntity;
import com.foxmind.stock.infrastructure.persistence.mongo.SpringProductMongoRepository;

import reactor.core.publisher.Mono;

@Repository
public class ProductPersistenceRepository implements ProductRepository {

    private final SpringProductMongoRepository repository;

    public ProductPersistenceRepository(SpringProductMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ProductEntity> save(ProductEntity entity) throws ProductTransactionalErrorException {
        return this.repository.save(entity)
            .onErrorMap(exception -> new ProductTransactionalErrorException("Error to database connection on save product ", exception));
    }
}
