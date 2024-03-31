package com.shopallday.storage.app.services.orders;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.OrderLineDto;
import com.shopallday.storage.app.services.BaseService;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.usecases.orders.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OrderLinesServiceImpl extends BaseService implements OrderLinesService {
    private final CreateSingleOrderLineUseCase createSingleOrderLineUseCase;
    private final DeleteSingleOrderLineUseCase deleteSingleOrderLineUseCase;
    private final GetOrderLineByIdUseCase getOrderLineByIdUseCase;
    private final UpdateOrderLineUseCase updateOrderLineUseCase;
    private final GetAllOrderLinesUseCase getAllOrderLinesUseCase;
    @Qualifier("orderLinesMapper")
    private final Mapper<OrderLine, OrderLineDto> mapper;

    public OrderLinesServiceImpl(CreateSingleOrderLineUseCase createSingleOrderLineUseCase,
                                 DeleteSingleOrderLineUseCase deleteSingleOrderLineUseCase,
                                 GetOrderLineByIdUseCase getOrderLineByIdUseCase,
                                 UpdateOrderLineUseCase updateOrderLineUseCase,
                                 GetAllOrderLinesUseCase getAllOrderLinesUseCase,
                                 @Qualifier("orderLinesMapper") Mapper<OrderLine, OrderLineDto> mapper) {
        this.createSingleOrderLineUseCase = createSingleOrderLineUseCase;
        this.deleteSingleOrderLineUseCase = deleteSingleOrderLineUseCase;
        this.getOrderLineByIdUseCase = getOrderLineByIdUseCase;
        this.updateOrderLineUseCase = updateOrderLineUseCase;
        this.getAllOrderLinesUseCase = getAllOrderLinesUseCase;
        this.mapper = mapper;
    }
    @Override
    @Transactional
    public OrderLineDto createOrderLine(OrderLineDto orderLineDto) throws CreateException {
        final OrderLine orderLine = mapper.mapFromDtoToDomain(orderLineDto);
        final OrderLine justCreatedOrderLine = createSingleOrderLineUseCase.execute(orderLine);
        return mapper.mapFromDomainToDto(justCreatedOrderLine);
    }

    @Override
    @Transactional
    public List<OrderLineDto> getOrderLines() {
        return mapper.mapFromDomainToDto(getAllOrderLinesUseCase.execute());
    }

    @Override
    @Transactional
    public OrderLineDto getOrderLineById(Long id) throws ReadException {
        final OrderLine orderline = getOrderLineByIdUseCase.execute(id);
        return mapper.mapFromDomainToDto(orderline);
    }

    @Override
    @Transactional
    public OrderLineDto updateOrderLine(OrderLineDto orderLineDto) throws ReadException, UpdateException {
        final OrderLine orderLine = mapper.mapFromDtoToDomain(orderLineDto);
        final OrderLine updatedOrderLine = updateOrderLineUseCase.execute(orderLine);
        return mapper.mapFromDomainToDto(updatedOrderLine);
    }

    @Override
    @Transactional
    public void deleteOrderLineById(Long id) throws DeleteException {
        deleteSingleOrderLineUseCase.execute(id);
    }

    @Override
    @Transactional
    public OrderLineDto partiallyUpdateOrderLine(Long id, Map<String, Object> fields) throws ReadException, UpdateException {
        OrderLine existingOrder = getOrderLineByIdUseCase.execute(id);
        updateFieldsOnObject(fields, existingOrder, OrderLine.class);
        return updateOrderLine(mapper.mapFromDomainToDto(existingOrder));
    }
}
