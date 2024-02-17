package com.shopallday.storage.domain.repository;

import com.shopallday.storage.domain.models.OrderStatusType;

import java.util.List;

public interface OrderStatusTypeRepository {
    void createOrderStatusType(final List<OrderStatusType> orderStatusTypes, RepositoryManager repositoryManager);
    List<OrderStatusType> getAllOrderStatusTypes();

    List<OrderStatusType> findOrderStatusTypeById(final List<Long> ids);

    void updateOrderStatusType(final OrderStatusType orderStatusType, RepositoryManager repositoryManager);

    void deleteOrderStatusType(final OrderStatusType orderStatusType);
}
