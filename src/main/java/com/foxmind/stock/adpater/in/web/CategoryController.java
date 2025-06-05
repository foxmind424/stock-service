package com.foxmind.stock.adpater.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxmind.stock.application.port.in.CategoryCommand;
import com.foxmind.stock.application.service.CategoryService;
import com.foxmind.stock.domain.dto.request.CategoryRequest;
import com.foxmind.stock.domain.dto.response.CategoryResponse;

import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "category")
public class CategoryController {
    
    private final CategoryCommand command;

    public CategoryController(CategoryService service) {
        this.command = service;
    }


    @PostMapping("create")
    public Mono<ResponseEntity<CategoryResponse>> create(@RequestBody CategoryRequest request) {
        return this.command.create(request)
            .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }
    
}
