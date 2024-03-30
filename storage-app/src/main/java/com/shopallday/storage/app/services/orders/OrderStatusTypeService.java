package com.shopallday.storage.app.services.orders;

import com.shopallday.storage.app.models.OrderStatusTypeDto;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;

import java.util.List;
import java.util.Map;

public interface OrderStatusTypeService {
    OrderStatusTypeDto createOrderStatusType(OrderStatusTypeDto orderStatusTypeDto) throws CreateException;
    List<OrderStatusTypeDto> getOrderStatusTypes();
    OrderStatusTypeDto getOrderStatusTypeById(Long id) throws ReadException;
    OrderStatusTypeDto updateOrderStatusType(OrderStatusTypeDto orderStatusTypeDto) throws ReadException, UpdateException;
    void deleteOrderStatusTypeById(Long id) throws DeleteException;
    OrderStatusTypeDto partiallyUpdateOrderStatusType(Long id, Map<String, Object> fields)
            throws ReadException, UpdateException;
}
