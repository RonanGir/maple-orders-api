package com.maplr.test.sugarshack.mapleordersapi.customer;

import com.maplr.test.sugarshack.mapleordersapi.common.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends CrudService<CustomerEntity, Long> {

    @Autowired
    protected CustomerService(CustomerRepository repository) {
        super(repository);
    }
}
