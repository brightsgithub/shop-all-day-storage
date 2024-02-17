package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.infra.entities.OrderStatusTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderStatusTypeMapper extends StorageMapper<OrderStatusTypeEntity, OrderStatusType> {

    OrderStatusTypeMapper INSTANCE = Mappers.getMapper(OrderStatusTypeMapper.class);

    @Override
    OrderStatusTypeEntity mapToEntity(OrderStatusType orderStatusType);

    @Override
    OrderStatusType mapToDomain(OrderStatusTypeEntity orderStatusTypeEntity);

    @Override
    List<OrderStatusTypeEntity> mapToEntity(List<OrderStatusType> orderStatusTypes);

    @Override
    List<OrderStatusType> mapToDomain(List<OrderStatusTypeEntity> orderStatusTypeEntities);
}
