package com.foxmind.stock.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxmind.stock.adpater.out.persistence.InventoryPersistenceRepository;
import com.foxmind.stock.application.exception.InventoryInternalErrorException;
import com.foxmind.stock.application.helper.ResponseMapper;
import com.foxmind.stock.application.port.in.InventoryCommand;
import com.foxmind.stock.application.port.out.InventoryRepository;
import com.foxmind.stock.domain.dto.request.InventoryRequest;
import com.foxmind.stock.domain.dto.response.InventoryResponse;
import com.foxmind.stock.domain.entity.InventoryEntity;

@Service
public class InventoryService implements InventoryCommand {
    
    private final InventoryRepository repository;
    private final ResponseMapper responseHelper;

    public InventoryService(InventoryPersistenceRepository repository, ResponseMapper mapper) {
        this.repository = repository;
        this.responseHelper = mapper;
    }
    
    @Override
    @Transactional
    public InventoryResponse create(InventoryRequest request) throws InventoryInternalErrorException {
        try {
            InventoryEntity entity = this.responseHelper.convertInventoryRequestToInventoryEntity(request);
            InventoryEntity saved = this.repository.save(entity);

            InventoryResponse response = this.responseHelper.convertIventoryEntityToInventoryResponse(saved);
            return response;            
        } catch (Exception exception) {
            throw new InventoryInternalErrorException("Error to save inventory", exception);
        }
    }
}
