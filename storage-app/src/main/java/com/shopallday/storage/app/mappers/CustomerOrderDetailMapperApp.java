package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.CustomerOrderDetailDto;
import com.shopallday.storage.domain.models.CustomerOrderDetail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.mapstruct.Mapper
public interface CustomerOrderDetailMapperApp extends Mapper<CustomerOrderDetail, CustomerOrderDetailDto> {
    @Override
    CustomerOrderDetailDto mapFromDomainToDto(CustomerOrderDetail customerOrderDetail);

    @Override
    CustomerOrderDetail mapFromDtoToDomain(CustomerOrderDetailDto Dto);

    @Override
    List<CustomerOrderDetailDto> mapFromDomainToDto(List<CustomerOrderDetail> customerOrderDetails);

    @Override
    List<CustomerOrderDetail> mapFromDtoToDomain(List<CustomerOrderDetailDto> customerOrderDetailDtos);
}
