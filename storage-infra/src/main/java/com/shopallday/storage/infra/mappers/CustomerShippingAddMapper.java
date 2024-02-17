package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.infra.entities.CustomerShippingAddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = CustomerMapper.class)
public interface CustomerShippingAddMapper extends StorageMapper<CustomerShippingAddressEntity, CustomerShippingAddress>{

    CustomerShippingAddMapper INSTANCE = Mappers.getMapper(CustomerShippingAddMapper.class);
    @Override
    @Mapping(source = "customer", target = "customerEntity") // needed for child mappings
    CustomerShippingAddressEntity mapToEntity(CustomerShippingAddress shippingAddress);
    @Override
    @Mapping(source = "customerEntity", target = "customer") // needed for child mappings
    CustomerShippingAddress mapToDomain(CustomerShippingAddressEntity shippingAddressEntity);
    @Override
    List<CustomerShippingAddressEntity> mapToEntity(List<CustomerShippingAddress> shippingAddresses);
    @Override
    List<CustomerShippingAddress> mapToDomain(List<CustomerShippingAddressEntity> shippingAddressEntities);



}
