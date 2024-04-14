package com.shopallday.storage.app.services.orders;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CustomerOrderDetailDto;
import com.shopallday.storage.app.models.OrderDto;
import com.shopallday.storage.app.services.BaseService;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.CustomerOrderDetail;
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
    private final GetCustomerOrderDetailUseCase getCustomerOrderDetailUseCase;
    @Qualifier("orderMapper")
    private final Mapper<Order, OrderDto> ordersMapper;
    @Qualifier("customerOrderDetailMapper")
    private final Mapper<CustomerOrderDetail, CustomerOrderDetailDto> customerOrderDetailMapper;

    public OrdersServiceImpl(CreateOrderUseCase createOrderUseCase,
                             CreateSingleOrderUseCase createSingleOrderUseCase,
                             DeleteOrderUseCase deleteOrderUseCase,
                             GetOrderByIdUseCase getOrderByIdUseCase,
                             UpdateOrderUseCase updateOrderUseCase,
                             GetAllOrdersUseCase getAllOrdersUseCase,
                             GetCustomerOrderDetailUseCase getCustomerOrderDetailUseCase,
                             @Qualifier("orderMapper") Mapper<Order, OrderDto> ordersMapper,
                             @Qualifier("customerOrderDetailMapper") Mapper<CustomerOrderDetail, CustomerOrderDetailDto> customerOrderDetailMapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.createSingleOrderUseCase = createSingleOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
        this.getAllOrdersUseCase = getAllOrdersUseCase;
        this.getCustomerOrderDetailUseCase = getCustomerOrderDetailUseCase;
        this.ordersMapper = ordersMapper;
        this.customerOrderDetailMapper = customerOrderDetailMapper;
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) throws CreateException {
        final Order order = ordersMapper.mapFromDtoToDomain(orderDto);
        final Order justCreatedOrder = createSingleOrderUseCase.execute(order);
        return ordersMapper.mapFromDomainToDto(justCreatedOrder);
    }

    @Override
    @Transactional
    public List<OrderDto> getOrders() {
        return ordersMapper.mapFromDomainToDto(getAllOrdersUseCase.execute());
    }

    @Override
    @Transactional
    public OrderDto getOrderById(Long id) throws ReadException {
        final Order order = getOrderByIdUseCase.execute(id);
        return ordersMapper.mapFromDomainToDto(order);
    }

    @Override
    @Transactional
    public OrderDto updateOrder(OrderDto orderDto) throws ReadException, UpdateException {
        final Order order = ordersMapper.mapFromDtoToDomain(orderDto);
        final Order updatedOrder = updateOrderUseCase.execute(order);
        return ordersMapper.mapFromDomainToDto(updatedOrder);
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
        return updateOrder(ordersMapper.mapFromDomainToDto(existingOrder));
    }

    @Override
    @Transactional
    public List<CustomerOrderDetailDto> getCustomerOrderDetailsById(Long customerId) throws ReadException {
        final List<CustomerOrderDetail> customerOrderDetails = getCustomerOrderDetailUseCase.execute(customerId);
        return customerOrderDetailMapper.mapFromDomainToDto(customerOrderDetails);
    }
}
