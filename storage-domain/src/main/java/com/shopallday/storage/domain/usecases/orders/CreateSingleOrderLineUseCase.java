package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class CreateSingleOrderLineUseCase implements UseCase<OrderLine, OrderLine> {

    private OrderLinesRepository repository;
    private RepositoryManager repositoryManager;

    public CreateSingleOrderLineUseCase(
            OrderLinesRepository repository,
            RepositoryManager repositoryManager) {
        this.repository = repository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public OrderLine execute(OrderLine orderLine) throws CreateException {
        return repository.createOrderLine(orderLine, repositoryManager);
    }
}
