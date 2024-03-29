package com.shopallday.storage.domain.usecases.orderstatustype;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.ORDER_STATUS_TYPE_COULD_NOT_BE_UPDATED;
import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.ORDER_STATUS_TYPE_NOT_FOUND;

public class UpdateOrderStatusTypeUseCase implements UseCase<OrderStatusType, OrderStatusType> {

    private final OrderStatusTypeRepository repository;

    public UpdateOrderStatusTypeUseCase(OrderStatusTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderStatusType execute(OrderStatusType orderStatusType) throws ReadException, UpdateException {
        try {
            final Long id = orderStatusType.getOrderStatusTypeId();
            if (id == null || !repository.isExists(id)) {
                throw new ReadException("Cannot find ProductType with id "+id, ORDER_STATUS_TYPE_NOT_FOUND);
            }

            return repository.updateOrderStatusType(orderStatusType);
        } catch (ReadException e) {
            throw e;
        }
        catch (Exception e) {
            throw new UpdateException("OrderStatusType with id "+orderStatusType.getOrderStatusTypeId()+" could not be updated",
                    ORDER_STATUS_TYPE_COULD_NOT_BE_UPDATED);
        }
    }
}
