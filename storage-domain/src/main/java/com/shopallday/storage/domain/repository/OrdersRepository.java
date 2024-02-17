package com.shopallday.storage.domain.repository;

import com.shopallday.storage.domain.models.Order;

import java.util.List;

public interface OrdersRepository {

    void createOrder(final List<Order> orders, RepositoryManager repositoryManager);
    List<Order> getAllOrders();

    List<Order> findOrdersById(final List<Long> ids);

    void updateOrder(final Order order, RepositoryManager repositoryManager);

    void deleteOrder(final Order order);

}
