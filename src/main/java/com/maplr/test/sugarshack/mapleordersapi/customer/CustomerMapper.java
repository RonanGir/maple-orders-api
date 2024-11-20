package com.maplr.test.sugarshack.mapleordersapi.customer;

import com.maplr.test.sugarshack.mapleordersapi.common.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface CustomerMapper extends BaseMapper<CustomerEntity, CustomerDto> {

    @Named("customerDtoToEntity")
    CustomerEntity dtoToEntity(CustomerDto dto);

    @Named("customerEntityToDto")
    CustomerDto entityToDto(CustomerEntity entity);
}
