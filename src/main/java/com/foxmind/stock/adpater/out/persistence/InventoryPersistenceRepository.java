package com.foxmind.stock.adpater.out.persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
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
    private final ReactiveMongoTemplate mongoTemplate;

    public InventoryPersistenceRepository(SpringInventoryMongoRepository repository, ReactiveMongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<InventoryEntity> save(InventoryEntity entity) {
        return this.repository.save(entity)
            .onErrorMap(exception ->
                new InventoryTransactionErrorException("Error to database connection on save inventory", exception));
    }

    @Override
    public Flux<InventoryEntity> list(int page, int size) throws InventoryTransactionErrorException {
        Query query = new Query()
            .skip((long) page * size)
            .limit(size)
            .with(Sort.by(Sort.Direction.ASC, "name"));
        return mongoTemplate.find(query, InventoryEntity.class)
            .onErrorMap(exception -> 
                new InventoryTransactionErrorException("Error to database connection on list inventory", exception));
    }

    @Override
    public Mono<InventoryEntity> byId(String id) throws InventoryTransactionErrorException {
        return this.repository.findById(id)
            .onErrorMap(exception ->
                new InventoryTransactionErrorException("Error to database connection on get inventory by id", exception));
    }

    @Override
    public Mono<InventoryEntity> byName(String name) throws InventoryTransactionErrorException {
        return this.repository.findByName(name)
            .onErrorMap(exception ->
                new InventoryTransactionErrorException("Error to database connection on get inventory by name", exception));
    }

    @Override
    public Mono<Void> deleteById(String id) throws InventoryTransactionErrorException {
        return this.repository.deleteById(id)
            .onErrorMap(exception -> new InventoryTransactionErrorException("Error to database connection on delete inventory by id", exception));
    }

}