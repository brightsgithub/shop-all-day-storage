package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

public class GetOrdersByCustomerIdUseCase implements UseCase<List<Order>, List<Long>> {

    private OrdersRepository ordersRepository;

    public GetOrdersByCustomerIdUseCase(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<Order> execute(List<Long> ids) throws Exception {
        return ordersRepository.findOrdersById(ids);
    }
}
