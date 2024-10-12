package com.maplr.test.sugarshack.mapleordersapi.service;

import com.maplr.test.sugarshack.mapleordersapi.model.entity.CustomerEntity;
import com.maplr.test.sugarshack.mapleordersapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends CrudService<CustomerEntity, Long> {

    @Autowired
    protected CustomerService(CustomerRepository repository) {
        super(repository);
    }
}
