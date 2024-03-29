package com.shopallday.storage.infra.repository.orders;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.infra.entities.OrderStatusTypeEntity;
import com.shopallday.storage.infra.mappers.OrderStatusTypeMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderStatusTypeRepository extends JpaRepository<OrderStatusTypeEntity, Long>, OrderStatusTypeRepository {

    OrderStatusTypeMapper mapper = OrderStatusTypeMapper.INSTANCE;
    @Override
    default List<OrderStatusType> createOrderStatusType(final List<OrderStatusType> orderStatusTypes) {
        return mapper.mapToDomain(saveAll(mapper.mapToEntity(orderStatusTypes)));
    }

    @Override
    default OrderStatusType createOrderStatusType(final OrderStatusType orderStatusType) {
        return mapper.mapToDomain(save(mapper.mapToEntity(orderStatusType)));
    }

    @Override
    default List<OrderStatusType> getAllOrderStatusTypes() {
        return mapper.mapToDomain(findAll());
    }

    @Override
    default List<OrderStatusType> findOrderStatusTypeById(final List<Long> ids) {
        return mapper.mapToDomain(findAllById(ids));
    }

    @Override
    default OrderStatusType findOrderStatusTypeById(Long id) throws ReadException {
        return mapper.mapToDomain(
                findById(id).orElseThrow(() -> new ReadException(
                        "Id "+id+" does not exist", BusinessErrorCodes.ORDER_STATUS_TYPE_NOT_FOUND
                )));
    }

    @Override
    default OrderStatusType updateOrderStatusType(final OrderStatusType orderStatusType) {
        return createOrderStatusType(orderStatusType);
    }

    @Override
    default void deleteOrderStatusType(final Long id) {
        deleteById(id);
    }
    @Override
    default boolean isExists(Long id) {
        return existsById(id);
    }
}
