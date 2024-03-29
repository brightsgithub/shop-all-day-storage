package com.shopallday.storage.domain.usecases.orderstatustype;

import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

import java.util.List;

public class CreateOrderStatusTypeUseCase implements UseCaseNoReturnVal<List<OrderStatusType>> {

    private OrderStatusTypeRepository orderStatusTypeRepository;
    private RepositoryManager repositoryManager;

    public CreateOrderStatusTypeUseCase(
            OrderStatusTypeRepository orderStatusTypeRepository,
            RepositoryManager repositoryManager
    ) {
        this.orderStatusTypeRepository = orderStatusTypeRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public void execute(List<OrderStatusType> orderStatusTypes) throws Exception {
        orderStatusTypeRepository.createOrderStatusType(orderStatusTypes);
    }
}
