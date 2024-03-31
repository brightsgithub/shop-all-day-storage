package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

public class DeleteSingleOrderLineUseCase implements UseCaseNoReturnVal<Long> {
    private final OrderLinesRepository repository;

    public DeleteSingleOrderLineUseCase(OrderLinesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Long id) throws DeleteException {
        try{
            repository.deleteOrderLine(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException("OrderLine with id "+ id+" could not be deleted", BusinessErrorCodes.ORDER_LINE_COULD_NOT_BE_DELETED);
        }
    }
}
