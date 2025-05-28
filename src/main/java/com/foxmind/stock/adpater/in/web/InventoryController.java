package com.foxmind.stock.adpater.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxmind.stock.application.port.in.InventoryCommand;
import com.foxmind.stock.application.port.in.InventoryQuery;
import com.foxmind.stock.application.service.InventoryService;
import com.foxmind.stock.domain.dto.request.InventoryRequest;
import com.foxmind.stock.domain.dto.response.InventoryResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "inventory")
public class InventoryController {

    private final InventoryCommand command;
    private final InventoryQuery query;

    public InventoryController(InventoryService service) {
        this.command = service;
        this.query = service;
    }

    @PostMapping("create")
    public Mono<ResponseEntity<InventoryResponse>> create(@RequestBody InventoryRequest request) {
        return this.command.create(request)
            .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }
      
    @GetMapping("list/all")
    public ResponseEntity<Flux<InventoryResponse>> listAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Flux<InventoryResponse> response = this.query.listAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}