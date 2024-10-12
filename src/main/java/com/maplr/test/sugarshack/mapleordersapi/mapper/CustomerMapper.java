package com.maplr.test.sugarshack.mapleordersapi.mapper;

import com.maplr.test.sugarshack.mapleordersapi.model.dto.CustomerDto;
import com.maplr.test.sugarshack.mapleordersapi.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface CustomerMapper extends BaseMapper<CustomerEntity, CustomerDto> {

    @Named("customerDtoToEntity")
    CustomerEntity dtoToEntity(CustomerDto dto);

    @Named("customerEntityToDto")
    CustomerDto entityToDto(CustomerEntity entity);
}
