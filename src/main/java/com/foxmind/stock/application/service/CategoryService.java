package com.foxmind.stock.application.service;

import org.springframework.stereotype.Service;

import com.foxmind.stock.application.exception.CategoryInternalErrorException;
import com.foxmind.stock.application.exception.InventoryNotFoundErrorException;
import com.foxmind.stock.application.helper.CategoryResponseMapper;
import com.foxmind.stock.application.helper.InventoryResponseMapper;
import com.foxmind.stock.application.port.in.CategoryCommand;
import com.foxmind.stock.application.port.out.CategoryRepository;
import com.foxmind.stock.application.port.out.InventoryRepository;
import com.foxmind.stock.domain.dto.request.CategoryRequest;
import com.foxmind.stock.domain.dto.response.CategoryResponse;
import com.foxmind.stock.domain.entity.CategoryEntity;

import reactor.core.publisher.Mono;

@Service
public class CategoryService implements CategoryCommand {
    
    private final CategoryRepository repository;
    private final InventoryRepository inventoryRepository;
    private final InventoryResponseMapper inventoryResponseMapper;
    private final CategoryResponseMapper responseHelper;

    public CategoryService(
        CategoryRepository repository, 
        CategoryResponseMapper categoryResponseMapper, 
        InventoryRepository inventoryRepository,
        InventoryResponseMapper inventoryResponseMapper) {
        this.repository = repository;
        this.inventoryRepository = inventoryRepository;
        this.responseHelper = categoryResponseMapper;
        this.inventoryResponseMapper = inventoryResponseMapper;
    }

    @Override
    public Mono<CategoryResponse> create(CategoryRequest request) throws InventoryNotFoundErrorException, CategoryInternalErrorException {
       return this.inventoryRepository.byId(request.getInventoryId())
        .switchIfEmpty(Mono.error(new InventoryNotFoundErrorException("Inventory not found with ID: " + request.getInventoryId())))
        .flatMap(inventory -> {
            CategoryEntity entity = this.responseHelper.convertCategoryRequestToEntity(request);
            return this.repository.save(entity)
                .map(saved -> {
                    var inventoryResponse = this.inventoryResponseMapper.convertIventoryEntityToResponse(inventory);
                    return this.responseHelper.convertCategoryEntityToResponse(entity, inventoryResponse);
                });
        })
        .onErrorMap(exception -> new CategoryInternalErrorException("Error to save category", exception));
    }

}