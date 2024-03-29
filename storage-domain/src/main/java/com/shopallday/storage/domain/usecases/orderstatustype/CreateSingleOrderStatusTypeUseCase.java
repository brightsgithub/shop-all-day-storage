package com.shopallday.storage.domain.usecases.orderstatustype;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class CreateSingleOrderStatusTypeUseCase implements UseCase<OrderStatusType, OrderStatusType> {

    private final OrderStatusTypeRepository repository;

    public CreateSingleOrderStatusTypeUseCase(OrderStatusTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderStatusType execute(OrderStatusType param) throws CreateException {
        try {
            return repository.createOrderStatusType(param);
        } catch (Exception e) {
            throw new CreateException("Could not create OrderStatusType", BusinessErrorCodes.ORDER_STATUS_TYPE_COULD_NOT_BE_CREATED);
        }
    }
}
