package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.OrderStatusTypeDto;
import com.shopallday.storage.domain.models.OrderStatusType;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@org.mapstruct.Mapper
public interface OrderStatusTypeMapperApp extends Mapper<OrderStatusType, OrderStatusTypeDto> {
    OrderStatusTypeMapperApp INSTANCE = Mappers.getMapper(OrderStatusTypeMapperApp.class);

    @Override
    OrderStatusTypeDto mapFromDomainToDto(OrderStatusType domain);

    @Override
    OrderStatusType mapFromDtoToDomain(OrderStatusTypeDto Dto);

    @Override
    List<OrderStatusTypeDto> mapFromDomainToDto(List<OrderStatusType> domains);

    @Override
    List<OrderStatusType> mapFromDtoToDomain(List<OrderStatusTypeDto> dtos);
}

