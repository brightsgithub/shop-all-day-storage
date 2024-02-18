package com.shopallday.storage.domain.repository.orders;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.RepositoryManager;

import java.util.List;

public interface OrdersRepository {

    void createOrder(final List<Order> orders, RepositoryManager repositoryManager);
    List<Order> getAllOrders();

    List<Order> findOrdersById(final List<Long> ids);

    void updateOrder(final Order order, RepositoryManager repositoryManager);

    void deleteOrder(final Order order);

}
