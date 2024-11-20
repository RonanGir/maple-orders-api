package com.maplr.test.sugarshack.mapleordersapi.common;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class CrudService<T, I> {

    private final JpaRepository<T, I> repository;

    protected CrudService(JpaRepository<T, I> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(I id) throws EntityNotFoundException {
        return repository.findById(id)
                         .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
    }

    @Transactional
    public void deleteById(I id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    @Transactional
    public T update(I id, T entity) {
        if (repository.existsById(id)) {
            return this.save(entity);
        } else {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }
    }
}
