package com.foxmind.stock.infrastructure.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.foxmind.stock.domain.entity.InventoryEntity;

public interface SpringInventoryMongoRepository extends MongoRepository<InventoryEntity, String> {}