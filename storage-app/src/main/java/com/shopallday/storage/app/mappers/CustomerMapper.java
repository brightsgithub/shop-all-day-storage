package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.domain.models.Customer;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.mapstruct.Mapper
public interface CustomerMapper extends Mapper<Customer, CustomerDto> {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Override
    CustomerDto mapFromDomainToDto(Customer customer);

    @Override
    Customer mapFromDtoToDomain(CustomerDto Dto);

    @Override
    List<CustomerDto> mapFromDomainToDto(List<Customer> customers);

    @Override
    List<Customer> mapFromDtoToDomain(List<CustomerDto> customerDtos);
}
