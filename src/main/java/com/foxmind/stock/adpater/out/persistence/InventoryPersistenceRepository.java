package com.foxmind.stock.adpater.out.persistence;

import org.springframework.stereotype.Repository;

import com.foxmind.stock.application.exception.InventoryTransactionErrorException;
import com.foxmind.stock.application.port.out.InventoryRepository;
import com.foxmind.stock.domain.entity.InventoryEntity;
import com.foxmind.stock.infrastructure.persistence.mongo.SpringInventoryMongoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class InventoryPersistenceRepository implements InventoryRepository {

    private final SpringInventoryMongoRepository repository;

    public InventoryPersistenceRepository(SpringInventoryMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<InventoryEntity> save(InventoryEntity entity) {
        return this.repository.save(entity)
            .onErrorMap(exception ->
                new InventoryTransactionErrorException("Error to database connection on save inventory", exception));
    }

    @Override
    public Flux<InventoryEntity> list(int page, int size) throws InventoryTransactionErrorException {
        int skip = (page - 1) * size;
        return this.repository.findAll()
            .skip(skip)
            .take(size)
            .onErrorMap(exception ->
                new InventoryTransactionErrorException("Error to database connection on save inventory", exception));
    }

}