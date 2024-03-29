package com.shopallday.storage.app.services.products;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.OrderStatusTypeDto;
import com.shopallday.storage.app.services.BaseService;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.usecases.orderstatustype.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OrderStatusTypeServiceImpl extends BaseService implements OrderStatusTypeService {
    private final CreateOrderStatusTypeUseCase createOrderStatusTypeUseCase;
    private final CreateSingleOrderStatusTypeUseCase createSingleOrderStatusTypeUseCase;
    private final DeleteOrderStatusTypeUseCase deleteOrderStatusTypeUseCase;
    private final GetOrderStatusTypeByIdUseCase getOrderStatusTypeByIdUseCase;
    private final UpdateOrderStatusTypeUseCase updateOrderStatusTypeUseCase;
    private final GetAllOrderStatusTypesUseCase getAllOrderStatusTypesUseCase;
    @Qualifier("orderStatusTypeMapper")
    private final Mapper<OrderStatusType, OrderStatusTypeDto> mapper;

    public OrderStatusTypeServiceImpl(CreateOrderStatusTypeUseCase createOrderStatusTypeUseCase,
                                      CreateSingleOrderStatusTypeUseCase createSingleOrderStatusTypeUseCase,
                                      DeleteOrderStatusTypeUseCase deleteOrderStatusTypeUseCase,
                                      GetOrderStatusTypeByIdUseCase getOrderStatusTypeByIdUseCase,
                                      UpdateOrderStatusTypeUseCase updateOrderStatusTypeUseCase,
                                      GetAllOrderStatusTypesUseCase getAllOrderStatusTypesUseCase,
                                      @Qualifier("orderStatusTypeMapper") Mapper<OrderStatusType, OrderStatusTypeDto> mapper) {
        this.createOrderStatusTypeUseCase = createOrderStatusTypeUseCase;
        this.createSingleOrderStatusTypeUseCase = createSingleOrderStatusTypeUseCase;
        this.deleteOrderStatusTypeUseCase = deleteOrderStatusTypeUseCase;
        this.getOrderStatusTypeByIdUseCase = getOrderStatusTypeByIdUseCase;
        this.updateOrderStatusTypeUseCase = updateOrderStatusTypeUseCase;
        this.getAllOrderStatusTypesUseCase = getAllOrderStatusTypesUseCase;
        this.mapper = mapper;
    }

    @Override
    public OrderStatusTypeDto createOrderStatusType(OrderStatusTypeDto orderStatusTypeDto) throws CreateException {
        final OrderStatusType orderStatusType = mapper.mapFromDtoToDomain(orderStatusTypeDto);
        final OrderStatusType justCreatedProductType = createSingleOrderStatusTypeUseCase.execute(orderStatusType);
        return mapper.mapFromDomainToDto(justCreatedProductType);
    }

    @Override
    public List<OrderStatusTypeDto> getOrderStatusTypes() {
        return mapper.mapFromDomainToDto(getAllOrderStatusTypesUseCase.execute());
    }

    @Override
    public OrderStatusTypeDto getOrderStatusTypeById(Long id) throws ReadException {
        final OrderStatusType orderStatusType= getOrderStatusTypeByIdUseCase.execute(id);
        return mapper.mapFromDomainToDto(orderStatusType);
    }

    @Override
    public OrderStatusTypeDto updateOrderStatusType(OrderStatusTypeDto orderStatusTypeDto) throws ReadException, UpdateException {
        final OrderStatusType orderStatusType = mapper.mapFromDtoToDomain(orderStatusTypeDto);
        final OrderStatusType updatedOrderStatusType = updateOrderStatusTypeUseCase.execute(orderStatusType);
        return mapper.mapFromDomainToDto(updatedOrderStatusType);
    }

    @Override
    public void deleteOrderStatusTypeById(Long id) throws DeleteException {
        deleteOrderStatusTypeUseCase.execute(id);
    }

    @Override
    public OrderStatusTypeDto partiallyUpdateOrderStatusType(Long id, Map<String, Object> fields) throws ReadException, UpdateException {
        OrderStatusType existingOrderStatusType = getOrderStatusTypeByIdUseCase.execute(id);
        updateFieldsOnObject(fields, existingOrderStatusType, OrderStatusType.class);
        return updateOrderStatusType(mapper.mapFromDomainToDto(existingOrderStatusType));
    }
}
