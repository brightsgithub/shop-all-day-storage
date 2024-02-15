package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.infra.entities.CustomerShippingAddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = CustomerMapper.class)
public interface CustomerShippingAddMapper {

    CustomerShippingAddMapper INSTANCE = Mappers.getMapper(CustomerShippingAddMapper.class);

    @Mapping(source = "customer", target = "customerEntity") // needed for child mappings
    CustomerShippingAddressEntity mapToEntity(CustomerShippingAddress shippingAddress);

    @Mapping(source = "customerEntity", target = "customer") // needed for child mappings
    CustomerShippingAddress mapToDomain(CustomerShippingAddressEntity shippingAddressEntity);

    List<CustomerShippingAddressEntity> mapToEntity(List<CustomerShippingAddress> shippingAddresses);
    List<CustomerShippingAddress> mapToDomain(List<CustomerShippingAddressEntity> shippingAddressEntities);

}
