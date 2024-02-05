package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.infra.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerEntity customerToEntity(Customer customer);
    Customer entityToCustomer(CustomerEntity entity);
}
