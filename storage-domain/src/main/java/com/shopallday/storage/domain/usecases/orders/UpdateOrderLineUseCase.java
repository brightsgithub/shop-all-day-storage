package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.*;

public class UpdateOrderLineUseCase implements UseCase<OrderLine, OrderLine> {

    private final OrderLinesRepository repository;
    private final RepositoryManager repositoryManager;

    public UpdateOrderLineUseCase(OrderLinesRepository repository, RepositoryManager repositoryManager) {
        this.repository = repository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public OrderLine execute(OrderLine orderLine) throws ReadException, UpdateException {
        try {
            final Long id = orderLine.getOrderLinesId();
            if (id == null || !repository.isExists(id)) {
                throw new ReadException("Cannot find Order with id "+id, ORDER_LINE_NOT_FOUND);
            }

            return repository.updateOrderLine(orderLine, repositoryManager);
        } catch (ReadException e) {
            throw e;
        }
        catch (Exception e) {
            throw new UpdateException("OrderLine with id "+orderLine.getOrderLinesId()+" could not be updated",
                    ORDER_LINE_COULD_NOT_BE_UPDATED);
        }
    }
}
