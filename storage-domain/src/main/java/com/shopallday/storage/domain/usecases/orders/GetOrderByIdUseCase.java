package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.usecases.UseCase;
import java.util.List;
import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.ORDER_NOT_FOUND;
public class GetOrderByIdUseCase implements UseCase<Order, Long> {
    private OrdersRepository ordersRepository;

    public GetOrderByIdUseCase(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Order execute(Long id) throws ReadException {
        List<Order> list = ordersRepository.findOrdersById(List.of(id));
        if (list.isEmpty()) {
            throw new ReadException("Order with id "+id+" could not be found.", ORDER_NOT_FOUND);
        }
        return list.get(0);
    }
}
