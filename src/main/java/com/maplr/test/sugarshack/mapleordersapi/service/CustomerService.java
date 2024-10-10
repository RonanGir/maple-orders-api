package com.maplr.test.sugarshack.mapleordersapi.service;

import com.maplr.test.sugarshack.mapleordersapi.model.entity.CustomerEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
