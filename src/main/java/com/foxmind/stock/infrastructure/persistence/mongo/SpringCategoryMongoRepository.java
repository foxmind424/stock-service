package com.foxmind.stock.infrastructure.persistence.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.foxmind.stock.domain.entity.CategoryEntity;
  
public interface SpringCategoryMongoRepository extends ReactiveMongoRepository<CategoryEntity, String> {}
