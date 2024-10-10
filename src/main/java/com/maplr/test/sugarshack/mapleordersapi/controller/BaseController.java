package com.maplr.test.sugarshack.mapleordersapi.controller;

import com.maplr.test.sugarshack.mapleordersapi.service.CrudService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BaseController<T, S extends CrudService> {

    protected CrudService service;

    public BaseController(CrudService service) {
        this.service = service;
    }

    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

}
