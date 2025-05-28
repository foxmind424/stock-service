package com.foxmind.stock.infrastructure.persistence.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.foxmind.stock.domain.entity.InventoryEntity;

public interface SpringInventoryMongoRepository extends ReactiveMongoRepository<InventoryEntity, String> {}