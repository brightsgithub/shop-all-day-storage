package com.shopallday.storage.app.services.orders;

import com.shopallday.storage.app.models.OrderLineDto;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;

import java.util.List;
import java.util.Map;

public interface OrderLinesService {
    OrderLineDto createOrderLine(OrderLineDto orderLineDto) throws CreateException;
    List<OrderLineDto> getOrderLines();
    OrderLineDto getOrderLineById(Long id) throws ReadException;
    OrderLineDto updateOrderLine(OrderLineDto orderLineDto) throws ReadException, UpdateException;
    void deleteOrderLineById(Long id) throws DeleteException;
    OrderLineDto partiallyUpdateOrderLine(Long id, Map<String, Object> fields)
            throws ReadException, UpdateException;
}
