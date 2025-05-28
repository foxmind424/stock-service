package com.foxmind.stock.application.helper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.foxmind.stock.domain.dto.request.InventoryRequest;
import com.foxmind.stock.domain.dto.response.InventoryResponse;
import com.foxmind.stock.domain.entity.InventoryEntity;

@Component
public class InventoryResponseMapper {

    private final ModelMapper mapper = new ModelMapper();

    public InventoryEntity convertInventoryRequestToEntity(InventoryRequest request) {
        return this.mapper.map(request, InventoryEntity.class);
    }

    public InventoryResponse convertIventoryEntityToResponse(InventoryEntity entity) {
        return this.mapper.map(entity, InventoryResponse.class);
    }
    
}