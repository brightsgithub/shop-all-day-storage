package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

import java.util.List;

public class CreateOrderLineUseCase implements UseCaseNoReturnVal<List<OrderLine>> {

    private OrderLinesRepository orderLinesRepository;
    private RepositoryManager repositoryManager;

    public CreateOrderLineUseCase(
            OrderLinesRepository orderLinesRepository,
            RepositoryManager repositoryManager
    ) {
        this.orderLinesRepository = orderLinesRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public void execute(List<OrderLine> orderLines) throws Exception {
        orderLinesRepository.createOrderLine(orderLines, repositoryManager);
    }
}
