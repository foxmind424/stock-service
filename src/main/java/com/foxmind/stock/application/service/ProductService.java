package com.foxmind.stock.application.service;

import org.springframework.stereotype.Service;

import com.foxmind.stock.application.exception.CategoryInternalErrorException;
import com.foxmind.stock.application.exception.InventoryNotFoundErrorException;
import com.foxmind.stock.application.exception.ProductInternalErrorException;
import com.foxmind.stock.application.helper.CategoryResponseMapper;
import com.foxmind.stock.application.helper.ProductResponseMapper;
import com.foxmind.stock.application.port.in.ProductCommand;
import com.foxmind.stock.application.port.out.CategoryRepository;
import com.foxmind.stock.application.port.out.ProductRepository;
import com.foxmind.stock.domain.dto.request.ProductRequest;
import com.foxmind.stock.domain.dto.response.ProductResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductCommand {
    
    private final CategoryRepository categoryRepository;
    private final CategoryResponseMapper categoryResponseHelper;
    private final ProductRepository repository;
    private final ProductResponseMapper responseHelper;

    @Override
    public Mono<ProductResponse> create(ProductRequest request) throws ProductInternalErrorException {
        /*return this.categoryRepository.byId(request.getCategoryId())
        .switchIfEmpty(Mono.error(new InventoryNotFoundErrorException("Inventory not found with ID: " + request.getInventoryId())))
        .flatMap(inventory -> {
            CategoryEntity entity = this.responseHelper.convertCategoryRequestToEntity(request);
            return this.repository.save(entity)
                .map(saved -> {
                    var inventoryResponse = this.inventoryResponseMapper.convertIventoryEntityToResponse(inventory);
                    return this.responseHelper.convertCategoryEntityToResponse(entity, inventoryResponse);
                });
        })
        .onErrorMap(exception -> new CategoryInternalErrorException("Error to save category", exception));*/

        return null;
    }

}
