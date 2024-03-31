package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.OrderLineDto;
import com.shopallday.storage.domain.models.OrderLine;
import org.mapstruct.Mapping;

import java.util.List;

@org.mapstruct.Mapper(uses ={OrderMapperApp.class, ProductMapper.class})
public interface OrderLinesMapperApp extends Mapper<OrderLine, OrderLineDto> {

    @Override
    @Mapping(source = "order", target = "orderDto")
    @Mapping(source = "product", target = "productDto")
    OrderLineDto mapFromDomainToDto(OrderLine domain);

    @Override
    @Mapping(source = "orderDto", target = "order")
    @Mapping(source = "productDto", target = "product")
    OrderLine mapFromDtoToDomain(OrderLineDto Dto);

    @Override
    List<OrderLineDto> mapFromDomainToDto(List<OrderLine> domains);

    @Override
    List<OrderLine> mapFromDtoToDomain(List<OrderLineDto> dtos);
}
