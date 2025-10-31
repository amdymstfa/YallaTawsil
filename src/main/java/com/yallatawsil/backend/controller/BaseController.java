package com.yallatawsil.backend.controller;

import com.yallatawsil.backend.service.BaseService.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
public abstract class BaseController<RequestDTO, ResponseDTO, ID> {

    protected final BaseService<RequestDTO, ResponseDTO, ID> service;

    protected BaseController(BaseService<RequestDTO, ResponseDTO, ID> service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create a new entity")
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody RequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get entity by ID")
    public ResponseEntity<ResponseDTO> getById(@PathVariable ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "Get all entities")
    public ResponseEntity<List<ResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an entity")
    public ResponseEntity<ResponseDTO> update(@PathVariable ID id, @Valid @RequestBody RequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an entity")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}