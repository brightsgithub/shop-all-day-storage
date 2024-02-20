package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoParam;

import java.util.List;

public class GetAllOrderStatusTypesUseCase implements UseCaseNoParam<List<OrderStatusType>> {

    private OrderStatusTypeRepository orderStatusTypeRepository;

    public GetAllOrderStatusTypesUseCase(OrderStatusTypeRepository orderStatusTypeRepository) {
        this.orderStatusTypeRepository = orderStatusTypeRepository;
    }

    @Override
    public List<OrderStatusType> execute() throws Exception {
        return orderStatusTypeRepository.getAllOrderStatusTypes();
    }
}
