package com.shopallday.storage.domain.repository.orders;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.RepositoryManager;

import java.util.List;

public interface OrdersRepository {

    List<Order> createOrder(final List<Order> orders, RepositoryManager repositoryManager);

    Order createOrder(Order order, RepositoryManager repositoryManager);

    List<Order> getAllOrders();

    List<Order> findOrdersById(final List<Long> ids);

    Order updateOrder(final Order order, RepositoryManager repositoryManager);

    void deleteOrder(final Long id);

    boolean isExists(Long id);
}
