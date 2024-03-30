package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class CreateSingleOrderUseCase implements UseCase<Order, Order> {
    private OrdersRepository ordersRepository;
    private RepositoryManager repositoryManager;

    public CreateSingleOrderUseCase(
            OrdersRepository ordersRepository,
            RepositoryManager repositoryManager) {
        this.ordersRepository = ordersRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public Order execute(Order orders) throws CreateException {
        return ordersRepository.createOrder(orders, repositoryManager);
    }
}
