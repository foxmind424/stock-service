package com.foxmind.stock.adpater.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxmind.stock.application.port.in.InventoryCommand;
import com.foxmind.stock.application.port.in.InventoryQuery;
import com.foxmind.stock.application.service.InventoryService;
import com.foxmind.stock.domain.dto.request.InventoryRequest;
import com.foxmind.stock.domain.dto.response.InventoryResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(value = "inventory")
@Tag(name = "Inventory Controller", description = "CRUD of inventory")
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

    @GetMapping("find/byId/{id}")
    public Mono<ResponseEntity<InventoryResponse>> findById(@PathVariable("id") String id) {
        return this.query.findById(id)
            .map(response -> ResponseEntity.status(HttpStatus.OK).body(response));
    }

    @GetMapping("find/byName/{name}")
    public Mono<ResponseEntity<InventoryResponse>> findByname(@PathVariable("name") String name) {
        return this.query.findByName(name)
            .map(response -> ResponseEntity.status(HttpStatus.OK).body(response));
    }
    
    @PutMapping("update/byId/{id}")
    public Mono<ResponseEntity<InventoryResponse>> updateById(@PathVariable("id") String id, @RequestBody InventoryRequest request) {
        return this.command.updateById(request, id)
            .map(response -> ResponseEntity.status(HttpStatus.OK).body(response));
    }

    @PutMapping("update/byName/{name}")
    public Mono<ResponseEntity<InventoryResponse>> updateByName(@PathVariable("name") String name, @RequestBody InventoryRequest request) {
        return this.command.updateByName(request, name)
            .map(response -> ResponseEntity.status(HttpStatus.OK).body(response));
    }

    @PatchMapping("updatePartial/byId/{id}")
    public Mono<ResponseEntity<InventoryResponse>> updatePartialById(@PathVariable("id") String id, @RequestBody InventoryRequest request) {
        return this.command.updatePartialById(request, id)
            .map(response -> ResponseEntity.status(HttpStatus.OK).body(response));
    }

    @PatchMapping("updatePartial/byName/{name}")
    public Mono<ResponseEntity<InventoryResponse>> updatePartialByName(@PathVariable("name") String name, @RequestBody InventoryRequest request) {
        return this.command.updatePartialByName(request, name)
            .map(response -> ResponseEntity.status(HttpStatus.OK).body(response));
    }

    @DeleteMapping("delete/byId/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, code = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable("id") String id) {
        return this.command.deleteById(id);
    }
    
}