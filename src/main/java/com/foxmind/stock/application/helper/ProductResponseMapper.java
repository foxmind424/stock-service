package com.foxmind.stock.application.helper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.foxmind.stock.domain.dto.request.ProductRequest;
import com.foxmind.stock.domain.dto.response.CategoryResponse;
import com.foxmind.stock.domain.dto.response.ProductResponse;
import com.foxmind.stock.domain.entity.ProductEntity;

@Component
public class ProductResponseMapper {
    
    private static final ModelMapper mapper = new ModelMapper();

    public ProductEntity convertProductRequestToEntity(ProductRequest request) {
        return mapper.map(request, ProductEntity.class);
    }

    public ProductResponse convertProductEntityToResponse(ProductEntity entity, CategoryResponse category) {
        ProductResponse response = mapper.map(entity, ProductResponse.class);
        response.setCategory(category);
        return response;
    }

}
