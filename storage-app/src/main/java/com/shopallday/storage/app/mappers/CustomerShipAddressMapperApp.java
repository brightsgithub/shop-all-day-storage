package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.CustomerShippingAddressDto;
import com.shopallday.storage.domain.models.CustomerShippingAddress;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.mapstruct.Mapper(uses = CustomerMapper.class)
public interface CustomerShipAddressMapperApp extends Mapper<CustomerShippingAddress, CustomerShippingAddressDto> {
    @Override
    @Mapping(source = "customer", target = "customerDto")
    CustomerShippingAddressDto mapFromDomainToDto(CustomerShippingAddress domain);

    @Override
    @Mapping(source = "customerDto", target = "customer")
    CustomerShippingAddress mapFromDtoToDomain(CustomerShippingAddressDto dto);

    @Override
    List<CustomerShippingAddressDto> mapFromDomainToDto(List<CustomerShippingAddress> domains);

    @Override
    List<CustomerShippingAddress> mapFromDtoToDomain(List<CustomerShippingAddressDto> dtos);
}
