package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

import java.util.List;

public class CreateOrderUseCase implements UseCaseNoReturnVal<List<Order>> {

    private OrdersRepository ordersRepository;
    private RepositoryManager repositoryManager;

    public CreateOrderUseCase(
            OrdersRepository ordersRepository,
            RepositoryManager repositoryManager) {
        this.ordersRepository = ordersRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public void execute(List<Order> orders) throws Exception {
        ordersRepository.createOrder(orders, repositoryManager);
    }
}
