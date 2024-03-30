package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.*;

public class UpdateOrderUseCase implements UseCase<Order, Order> {
    private OrdersRepository repository;
    private RepositoryManager repositoryManager;

    public UpdateOrderUseCase(
            OrdersRepository repository,
            RepositoryManager repositoryManager) {
        this.repository = repository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public Order execute(Order order) throws ReadException, UpdateException {
        try {
            final Long id = order.getOrderId();
            if (id == null || !repository.isExists(id)) {
                throw new ReadException("Cannot find Order with id "+id, ORDER_NOT_FOUND);
            }

            return repository.updateOrder(order, repositoryManager);
        } catch (ReadException e) {
            throw e;
        }
        catch (Exception e) {
            throw new UpdateException("Order with id "+order.getOrderId()+" could not be updated",
                    ORDER_COULD_NOT_BE_UPDATED);
        }
    }
}
