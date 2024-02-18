package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

public class GetOrdersByCustomerId implements UseCase<List<Order>, Long> {

    private OrdersRepository ordersRepository;
    private RepositoryManager repositoryManager;

    public GetOrdersByCustomerId(OrdersRepository ordersRepository, RepositoryManager repositoryManager) {
        this.ordersRepository = ordersRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public List<Order> execute(Long param) throws Exception {
        return null;
    }
}
