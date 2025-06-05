package com.foxmind.stock.adpater.out.persistence;

import org.springframework.stereotype.Repository;

import com.foxmind.stock.application.exception.CategoryTransactionalErrorException;
import com.foxmind.stock.application.port.out.CategoryRepository;
import com.foxmind.stock.domain.entity.CategoryEntity;
import com.foxmind.stock.infrastructure.persistence.mongo.SpringCategoryMongoRepository;

import reactor.core.publisher.Mono;

@Repository
public class CategoryPersistenceRepository implements CategoryRepository {
    
    private final SpringCategoryMongoRepository repository;

    public CategoryPersistenceRepository(SpringCategoryMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<CategoryEntity> save(CategoryEntity entity) throws CategoryTransactionalErrorException {
        return this.repository.save(entity)
            .onErrorMap(exception -> new CategoryTransactionalErrorException("Error to database connection on save category", exception));
    }

}
