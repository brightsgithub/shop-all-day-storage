package com.shopallday.storage.domain.repository.orders;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.OrderStatusType;

import java.util.List;

public interface OrderStatusTypeRepository {
    List<OrderStatusType> createOrderStatusType(final List<OrderStatusType> orderStatusTypes);
    OrderStatusType createOrderStatusType(final OrderStatusType orderStatusType);
    List<OrderStatusType> getAllOrderStatusTypes();

    List<OrderStatusType> findOrderStatusTypeById(final List<Long> ids);

    OrderStatusType findOrderStatusTypeById(Long id) throws ReadException;

    OrderStatusType updateOrderStatusType(final OrderStatusType orderStatusType);

    void deleteOrderStatusType(final Long id);

    boolean isExists(Long id);

    void deleteAll();
}
