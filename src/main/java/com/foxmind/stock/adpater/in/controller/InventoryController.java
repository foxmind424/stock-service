package com.foxmind.stock.adpater.in.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxmind.stock.application.port.in.InventoryCommand;
import com.foxmind.stock.application.service.InventoryService;
import com.foxmind.stock.domain.dto.request.InventoryRequest;
import com.foxmind.stock.domain.dto.response.InventoryResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("inventory")
public class InventoryController {

    private final InventoryCommand command;

    public InventoryController(InventoryService service) {
        this.command = service;
    }

    @PostMapping("create")
    public ResponseEntity<InventoryResponse> create(@RequestBody InventoryRequest request) {
        InventoryResponse response = this.command.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    
}