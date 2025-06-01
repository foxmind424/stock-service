package com.foxmind.stock.application.service;

import org.springframework.stereotype.Service;

import com.foxmind.stock.adpater.out.persistence.InventoryPersistenceRepository;
import com.foxmind.stock.application.exception.InventoryInternalErrorException;
import com.foxmind.stock.application.exception.InventoryNotFoundErrorException;
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

    @Override
    public Mono<InventoryResponse> findById(String id)
            throws InventoryNotFoundErrorException, InventoryInternalErrorException {
        return this.repository.byId(id)
            .switchIfEmpty(Mono.error(new InventoryNotFoundErrorException("Inventory not found with ID: " + id)))
            .map(responseHelper::convertIventoryEntityToResponse)
            .onErrorMap(exception -> {
                if (exception instanceof InventoryNotFoundErrorException) return exception;
                return new InventoryInternalErrorException("Error to find inventory by ID", exception);
            });
    }

    @Override
    public Mono<InventoryResponse> findByName(String name)
            throws InventoryNotFoundErrorException, InventoryInternalErrorException {
        return this.repository.byName(name)
            .switchIfEmpty(Mono.error(new InventoryNotFoundErrorException("Inventory not found with Name: " + name)))
            .map(responseHelper::convertIventoryEntityToResponse)
            .onErrorMap(exception -> {
                if (exception instanceof InventoryNotFoundErrorException) return exception;
                return new InventoryInternalErrorException("Error to find inventory by name", exception);
            });
    }

    @Override
    public Mono<InventoryResponse> updateById(InventoryRequest request, String id)
            throws InventoryNotFoundErrorException, InventoryInternalErrorException {
        return this.repository.byId(id)
            .switchIfEmpty(Mono.error(new InventoryNotFoundErrorException("Inventory not found with ID: " + id)))
            .flatMap(existingEntity -> {
                existingEntity.setName(request.getName());

                return this.repository.save(existingEntity);
            })
            .map(responseHelper::convertIventoryEntityToResponse)
            .onErrorMap(exception -> {
                if (exception instanceof InventoryNotFoundErrorException) return exception;
                return new InventoryInternalErrorException("Error to update inventory by id", exception);
            });
    }

    @Override
    public Mono<InventoryResponse> updateByName(InventoryRequest request, String name)
            throws InventoryNotFoundErrorException, InventoryInternalErrorException {
        return this.repository.byName(name)
            .switchIfEmpty(Mono.error(new InventoryNotFoundErrorException("Inventory not found with Name: " + name)))
            .flatMap(existingEntity -> {
                existingEntity.setName(request.getName());
                
                return this.repository.save(existingEntity);
            })
            .map(responseHelper::convertIventoryEntityToResponse)
            .onErrorMap(exception -> {
                if (exception instanceof InventoryNotFoundErrorException) return exception;
                return new InventoryInternalErrorException("Error to update inventory by name", exception);
            });
    }

    @Override
    public Mono<InventoryResponse> updatePartialByName(InventoryRequest request, String name)
            throws InventoryNotFoundErrorException, InventoryInternalErrorException {
        return this.repository.byName(name)
            .switchIfEmpty(Mono.error(new InventoryNotFoundErrorException("Inventory not found with Name: " + name)))
            .flatMap(existingEntity -> {
                if (request.getName() != null) {
                    existingEntity.setName(request.getName());
                }

                return this.repository.save(existingEntity);
            })
            .map(responseHelper::convertIventoryEntityToResponse)
            .onErrorMap(exception -> {
                if (exception instanceof InventoryNotFoundErrorException) return exception;
                return new InventoryInternalErrorException("Error to update partial inventory by name", exception);
            });
    }

    @Override
    public Mono<InventoryResponse> updatePartialById(InventoryRequest request, String id)
            throws InventoryNotFoundErrorException, InventoryInternalErrorException {
        return this.repository.byId(id)
            .switchIfEmpty(Mono.error(new InventoryNotFoundErrorException("Inventory not found with ID: " + id)))
            .flatMap(existingEntity -> {
                if (request.getName() != null) {
                    existingEntity.setName(request.getName());
                }

                return this.repository.save(existingEntity);
            })
            .map(responseHelper::convertIventoryEntityToResponse)
            .onErrorMap(exception -> {
                if (exception instanceof InventoryNotFoundErrorException) return exception;
                return new InventoryInternalErrorException("Error to update partial inventory by id", exception);
            });
    }

    @Override
    public Mono<Void> deleteById(String id) throws InventoryNotFoundErrorException, InventoryInternalErrorException {
        return this.repository.byId(id)
            .switchIfEmpty(Mono.error(new InventoryNotFoundErrorException("Inventory not found with ID: " + id)))
            .flatMap(existingEntity -> {
                return this.repository.deleteById(id)
                    .onErrorMap(exception -> new InventoryInternalErrorException("Error to delete inventory by id", exception));
            });
    }
}
