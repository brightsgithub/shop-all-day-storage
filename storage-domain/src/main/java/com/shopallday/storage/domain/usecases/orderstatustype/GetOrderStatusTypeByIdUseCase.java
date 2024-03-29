package com.shopallday.storage.domain.usecases.orderstatustype;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.usecases.UseCase;
public class GetOrderStatusTypeByIdUseCase implements UseCase<OrderStatusType, Long> {
    private final OrderStatusTypeRepository repository;

    public GetOrderStatusTypeByIdUseCase(OrderStatusTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderStatusType execute(Long param) throws ReadException {
        return repository.findOrderStatusTypeById(param);
    }
}
