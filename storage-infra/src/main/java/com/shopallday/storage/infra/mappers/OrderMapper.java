package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.infra.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CustomerMapper.class, OrderStatusTypeMapper.class})
public interface OrderMapper extends StorageMapper<OrderEntity, Order> {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Override
    @Mapping(source = "customer", target = "customerEntity")
    @Mapping(source = "orderStatusType", target = "orderStatusTypeEntity")
    OrderEntity mapToEntity(Order order);

    @Override
    @Mapping(source = "customerEntity", target = "customer")
    @Mapping(source = "orderStatusTypeEntity", target = "orderStatusType")
    Order mapToDomain(OrderEntity orderEntity);

    @Override

    List<OrderEntity> mapToEntity(List<Order> orders);

    @Override
    List<Order> mapToDomain(List<OrderEntity> orderEntities);
}
