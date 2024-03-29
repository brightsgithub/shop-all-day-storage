package com.shopallday.storage.domain.usecases.orderstatustype;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;
public class DeleteOrderStatusTypeUseCase implements UseCaseNoReturnVal<Long> {

    private final OrderStatusTypeRepository repository;

    public DeleteOrderStatusTypeUseCase(OrderStatusTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Long id) throws DeleteException {
        try{
            repository.deleteOrderStatusType(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException("OrderStatusType with id "+ id+" could not be deleted",
                    BusinessErrorCodes.ORDER_STATUS_TYPE_COULD_NOT_BE_DELETED);
        }
    }
}
