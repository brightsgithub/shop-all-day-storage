package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.infra.entities.OrderLineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {OrderMapper.class, ProductMapper.class})
public interface OrderLineMapper extends StorageMapper<OrderLineEntity, OrderLine> {

    OrderLineMapper INSTANCE = Mappers.getMapper(OrderLineMapper.class);

    @Override
    @Mapping(source = "order", target = "orderEntity")
    @Mapping(source = "product", target = "productEntity")
    OrderLineEntity mapToEntity(OrderLine orderLine);

    @Override
    @Mapping(source = "orderEntity", target = "order")
    @Mapping(source = "productEntity", target = "product")
    OrderLine mapToDomain(OrderLineEntity orderLineEntity);

    @Override
    List<OrderLineEntity> mapToEntity(List<OrderLine> orderLines);

    @Override
    List<OrderLine> mapToDomain(List<OrderLineEntity> orderLineEntities);
}
