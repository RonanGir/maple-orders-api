package com.maplr.test.sugarshack.mapleordersapi.controller;

import com.maplr.test.sugarshack.mapleordersapi.common.BaseMapper;
import com.maplr.test.sugarshack.mapleordersapi.common.CrudService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BaseController<O, T, I> {

    protected CrudService<T, I> service;
    protected BaseMapper<T, O> mapper;

    public BaseController(CrudService<T, I> service, BaseMapper<T, O> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public ResponseEntity<List<O>> findAll() {
        List<O> dtos = mapper.entitiesToDtos(service.findAll());
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<O> findById(I id) {
        try {
            O dto = mapper.entityToDto(service.findById(id));
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<O> create(O dto) {
        O newDto = mapper.entityToDto(service.save(mapper.dtoToEntity(dto)));
        return ResponseEntity.ok(newDto);
    }


}
