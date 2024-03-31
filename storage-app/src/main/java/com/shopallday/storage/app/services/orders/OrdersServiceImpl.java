package com.shopallday.storage.app.services.orders;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.OrderDto;
import com.shopallday.storage.app.services.BaseService;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.usecases.orders.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OrdersServiceImpl extends BaseService implements OrdersService {
    private final CreateOrderUseCase createOrderUseCase;
    private final CreateSingleOrderUseCase createSingleOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final GetAllOrdersUseCase getAllOrdersUseCase;
    @Qualifier("orderMapper")
    private final Mapper<Order, OrderDto> mapper;

    public OrdersServiceImpl(CreateOrderUseCase createOrderUseCase,
                             CreateSingleOrderUseCase createSingleOrderUseCase,
                             DeleteOrderUseCase deleteOrderUseCase,
                             GetOrderByIdUseCase getOrderByIdUseCase,
                             UpdateOrderUseCase updateOrderUseCase,
                             GetAllOrdersUseCase getAllOrdersUseCase,
                             @Qualifier("orderMapper") Mapper<Order, OrderDto> mapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.createSingleOrderUseCase = createSingleOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
        this.getAllOrdersUseCase = getAllOrdersUseCase;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) throws CreateException {
        final Order order = mapper.mapFromDtoToDomain(orderDto);
        final Order justCreatedOrder = createSingleOrderUseCase.execute(order);
        return mapper.mapFromDomainToDto(justCreatedOrder);
    }

    @Override
    @Transactional
    public List<OrderDto> getOrders() {
        return mapper.mapFromDomainToDto(getAllOrdersUseCase.execute());
    }

    @Override
    @Transactional
    public OrderDto getOrderById(Long id) throws ReadException {
        final Order order = getOrderByIdUseCase.execute(id);
        return mapper.mapFromDomainToDto(order);
    }

    @Override
    @Transactional
    public OrderDto updateOrder(OrderDto orderDto) throws ReadException, UpdateException {
        final Order order = mapper.mapFromDtoToDomain(orderDto);
        final Order updatedOrder = updateOrderUseCase.execute(order);
        return mapper.mapFromDomainToDto(updatedOrder);
    }

    @Override
    @Transactional
    public void deleteOrderById(Long id) throws DeleteException {
        deleteOrderUseCase.execute(id);
    }

    @Override
    @Transactional
    public OrderDto partiallyUpdateOrder(Long id, Map<String, Object> fields) throws ReadException, UpdateException {
        Order existingOrder = getOrderByIdUseCase.execute(id);
        updateFieldsOnObject(fields, existingOrder, Order.class);
        return updateOrder(mapper.mapFromDomainToDto(existingOrder));
    }
}
