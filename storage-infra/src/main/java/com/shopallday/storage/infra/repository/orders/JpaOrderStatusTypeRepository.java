package com.shopallday.storage.infra.repository.orders;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.infra.entities.OrderStatusTypeEntity;
import com.shopallday.storage.infra.mappers.OrderStatusTypeMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
        final List<Long> orderIds = getOrderIdConstraints(id);
        deleteOrdersOrderIds(orderIds);
        deleteOrderLinesByOrderIds(orderIds);
        deleteById(id);
    }
    @Query("SELECT distinct " +
            "o.orderId " +
            "FROM OrderEntity o," +
            "OrderStatusTypeEntity ost," +
            "OrderLineEntity ol " +
            "WHERE o.orderId = ol.orderEntity.orderId " +
            "AND o.orderStatusTypeEntity.orderStatusTypeId = ost.orderStatusTypeId " +
            "AND ost.orderStatusTypeId = :orderStatusTypeId")
    List<Long> getOrderIdConstraints(@Param("orderStatusTypeId") Long productTypeId);

    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM OrderEntity o WHERE o.orderId in :orderIds")
    void deleteOrdersOrderIds(@Param("orderIds") List<Long> orderIds);
    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM OrderLineEntity ol WHERE ol.orderEntity.orderId in :orderIds")
    void deleteOrderLinesByOrderIds(@Param("orderIds") List<Long> orderIds);

    @Override
    default boolean isExists(Long id) {
        return existsById(id);
    }
}
