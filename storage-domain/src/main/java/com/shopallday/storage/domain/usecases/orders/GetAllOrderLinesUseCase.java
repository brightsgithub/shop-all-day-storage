package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoParam;

import java.util.List;

public class GetAllOrderLinesUseCase implements UseCaseNoParam<List<OrderLine>> {
    private final OrderLinesRepository repository;

    public GetAllOrderLinesUseCase(OrderLinesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderLine> execute() {
        return repository.getAllOrderLines();
    }
}
