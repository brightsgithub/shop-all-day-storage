package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.infra.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper extends StorageMapper<CustomerEntity, Customer>{
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Override
    CustomerEntity mapToEntity(Customer customer);

    @Override
    Customer mapToDomain(CustomerEntity customerEntity);

    @Override
    List<CustomerEntity> mapToEntity(List<Customer> customers);

    @Override
    List<Customer> mapToDomain(List<CustomerEntity> customerEntities);
}
