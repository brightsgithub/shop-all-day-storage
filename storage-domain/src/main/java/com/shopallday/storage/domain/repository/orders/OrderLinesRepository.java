package com.shopallday.storage.domain.repository.orders;

import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.RepositoryManager;

import java.util.List;

public interface OrderLinesRepository {

    void createOrderLine(final List<OrderLine> orderLines, RepositoryManager repositoryManager);
    List<OrderLine> getAllOrderLines();

    List<OrderLine> findOrderLinesById(final List<Long> ids);

    void updateOrderLine(final OrderLine orderLine, RepositoryManager repositoryManager);

    void deleteOrderLine(final OrderLine orderLine);

}
