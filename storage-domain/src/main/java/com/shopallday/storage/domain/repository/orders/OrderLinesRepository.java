package com.shopallday.storage.domain.repository.orders;

import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.RepositoryManager;

import java.util.List;

public interface OrderLinesRepository {

    List<OrderLine> createOrderLine(final List<OrderLine> orderLines, RepositoryManager repositoryManager);

    OrderLine createOrderLine(OrderLine orderLine, RepositoryManager repositoryManager);

    List<OrderLine> getAllOrderLines();

    List<OrderLine> findOrderLinesById(final List<Long> ids);

    OrderLine updateOrderLine(final OrderLine orderLine, RepositoryManager repositoryManager);

    void deleteOrderLine(Long id);

    boolean isExists(Long id);

    void deleteAll();
}
