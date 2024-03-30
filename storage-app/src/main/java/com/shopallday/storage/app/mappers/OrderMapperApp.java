package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.OrderDto;
import com.shopallday.storage.domain.models.Order;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@org.mapstruct.Mapper(uses = {CustomerMapper.class, OrderStatusTypeMapperApp.class})
public interface OrderMapperApp extends Mapper<Order, OrderDto> {
    @Override
    @Mapping(source = "customer", target = "customerDto")
    @Mapping(source = "orderStatusType", target = "orderStatusTypeDto")
    OrderDto mapFromDomainToDto(Order domain);

    @Override
    Order mapFromDtoToDomain(OrderDto Dto);

    @Override
    List<OrderDto> mapFromDomainToDto(List<Order> domains);

    @Override
    List<Order> mapFromDtoToDomain(List<OrderDto> dtos);
}
