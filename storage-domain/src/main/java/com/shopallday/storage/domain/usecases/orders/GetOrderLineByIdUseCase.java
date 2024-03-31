package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.ORDER_LINE_NOT_FOUND;

public class GetOrderLineByIdUseCase implements UseCase<OrderLine, Long> {
    private final OrderLinesRepository repository;

    public GetOrderLineByIdUseCase(OrderLinesRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderLine execute(Long id) throws ReadException {
        List<OrderLine> list = repository.findOrderLinesById(List.of(id));
        if (list.isEmpty()) {
            throw new ReadException("OrderLine with id "+id+" could not be found.", ORDER_LINE_NOT_FOUND);
        }
        return list.get(0);
    }
}
