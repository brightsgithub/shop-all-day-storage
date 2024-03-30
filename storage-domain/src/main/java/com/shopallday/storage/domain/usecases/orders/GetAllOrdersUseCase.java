package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoParam;

import java.util.List;

public class GetAllOrdersUseCase implements UseCaseNoParam<List<Order>> {

    private OrdersRepository ordersRepository;

    public GetAllOrdersUseCase(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<Order> execute() {
        return ordersRepository.getAllOrders();
    }
}
