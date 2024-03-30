package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

public class DeleteOrderUseCase implements UseCaseNoReturnVal<Long> {

    private final OrdersRepository repository;

    public DeleteOrderUseCase(OrdersRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Long id) throws DeleteException {
        try{
            repository.deleteOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException("Order with id "+ id+" could not be deleted", BusinessErrorCodes.ORDER_COULD_NOT_BE_DELETED);
        }
    }
}
