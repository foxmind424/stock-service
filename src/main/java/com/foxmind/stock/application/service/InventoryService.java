package com.foxmind.stock.application.service;

import org.springframework.stereotype.Service;

import com.foxmind.stock.adpater.out.persistence.InventoryPersistenceRepository;
import com.foxmind.stock.application.exception.InventoryInternalErrorException;
import com.foxmind.stock.application.helper.InventoryResponseMapper;
import com.foxmind.stock.application.port.in.InventoryCommand;
import com.foxmind.stock.application.port.in.InventoryQuery;
import com.foxmind.stock.application.port.out.InventoryRepository;
import com.foxmind.stock.domain.dto.request.InventoryRequest;
import com.foxmind.stock.domain.dto.response.InventoryResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InventoryService implements InventoryCommand, InventoryQuery {

    private final InventoryRepository repository;
    private final InventoryResponseMapper responseHelper;

    public InventoryService(InventoryPersistenceRepository repository, InventoryResponseMapper mapper) {
        this.repository = repository;
        this.responseHelper = mapper;
    }

    @Override
    public Mono<InventoryResponse> create(InventoryRequest request) throws InventoryInternalErrorException {
        return Mono.just(request)
                .map(responseHelper::convertInventoryRequestToEntity)
                .flatMap(repository::save)
                .map(responseHelper::convertIventoryEntityToResponse)
                .onErrorMap(exception -> new InventoryInternalErrorException("Error to save inventory", exception));
    }

    @Override
    public Flux<InventoryResponse> listAll(int page, int size) throws InventoryInternalErrorException {
        return this.repository.list(page, size)
            .map(responseHelper::convertIventoryEntityToResponse)
            .onErrorMap(exception -> new InventoryInternalErrorException("Error to list all inventories", exception));
    }
}
