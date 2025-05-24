package com.foxmind.stock.adpater.out.persistence;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.foxmind.stock.application.exception.InventoryTransactionErrorException;
import com.foxmind.stock.application.port.out.InventoryRepository;
import com.foxmind.stock.domain.entity.InventoryEntity;
import com.foxmind.stock.infrastructure.persistence.mongo.SpringInventoryMongoRepository;

@Repository
public class InventoryPersistenceRepository implements InventoryRepository {
    
    private final SpringInventoryMongoRepository repository;

    public InventoryPersistenceRepository(SpringInventoryMongoRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public InventoryEntity save(InventoryEntity entity) throws InventoryTransactionErrorException {
        try {
            InventoryEntity response = this.repository.save(entity);
            return response;
        } catch (DataAccessException exception) {
            throw new InventoryTransactionErrorException("Error to database connection on save inventory", exception);
        }
    }
}