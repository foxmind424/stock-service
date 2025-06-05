package com.foxmind.stock.application.helper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.foxmind.stock.domain.dto.request.CategoryRequest;
import com.foxmind.stock.domain.dto.response.CategoryResponse;
import com.foxmind.stock.domain.dto.response.InventoryResponse;
import com.foxmind.stock.domain.entity.CategoryEntity;

@Component
public class CategoryResponseMapper {
    
    private static final ModelMapper mapper = new ModelMapper();

    public CategoryEntity convertCategoryRequestToEntity(CategoryRequest request) {
        return mapper.map(request, CategoryEntity.class);
    }

    public CategoryResponse convertCategoryEntityToResponse(CategoryEntity entity, InventoryResponse invenotry) {
        CategoryResponse response = mapper.map(entity, CategoryResponse.class);
        response.setInventory(invenotry);
        return response;
    }

}
