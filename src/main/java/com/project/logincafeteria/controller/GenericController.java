package com.project.logincafeteria.controller;

import com.project.logincafeteria.service.IGenericService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericController<T, DTO, ID> {

    protected final IGenericService<T, ID> service;
    protected final ModelMapper modelMapper;

    protected GenericController(IGenericService<T, ID> service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    // GET /resource
    @GetMapping
    public ResponseEntity<List<DTO>> findAll() throws Exception {
        List<T> entities = service.findAll();
        List<DTO> dtos = entities.stream()
                .map(entity -> modelMapper.map(entity, getDtoClass()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /resource/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DTO> findById(@PathVariable ID id) throws Exception {
        T entity = service.findById(id);
        DTO dto = modelMapper.map(entity, getDtoClass());
        return ResponseEntity.ok(dto);
    }

    // POST /resource
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody DTO dto) throws Exception {
        T entity = modelMapper.map(dto, getEntityClass());
        T saved = service.save(entity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(getId(saved))
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // PUT /resource/{id}
    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@PathVariable ID id, @Valid @RequestBody DTO dto) throws Exception {
        T entity = modelMapper.map(dto, getEntityClass());
        T updated = service.update(entity, id);
        DTO updatedDto = modelMapper.map(updated, getDtoClass());
        return ResponseEntity.ok(updatedDto);
    }

    // DELETE /resource/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos abstractos
    protected abstract Class<T> getEntityClass();

    protected abstract Class<DTO> getDtoClass();

    protected abstract ID getId(T entity);
}
