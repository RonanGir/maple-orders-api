package com.maplr.test.sugarshack.mapleordersapi.service;

import com.maplr.test.sugarshack.mapleordersapi.mapper.BaseMapper;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.BaseEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class CrudService<O, I extends Long, T extends BaseEntity, M extends BaseMapper, R extends JpaRepository> {

    private final JpaRepository repository;
    private final M mapper;

    protected CrudService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<O> findAll() {
       return mapper.entitiesToDtos(repository.findAll());
    }

    public O findById(I id) throws EntityNotFoundException {
        return (O) repository.findById(id)
                .map(mapper::entityToDto)
                .orElse(null);
    }

    public void deleteById(I id) {
        repository.deleteById(id);
    }

    public O save(O dto) {
        T entity = (T) mapper.dtoToEntity(dto);
        T savedEntity = (T) repository.save(entity);
        return (O) mapper.entityToDto(savedEntity);
    }

    public O update(I id, O dto) {
        if (repository.existsById(id)) {
            return this.save(dto);
        } else {
            throw new IllegalArgumentException("Entity not found");
        }
    }
}
