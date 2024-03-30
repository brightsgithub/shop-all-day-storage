package com.shopallday.storage.app.services.orders;

import com.shopallday.storage.app.models.OrderDto;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;

import java.util.List;
import java.util.Map;

public interface OrdersService {
    OrderDto createOrder(OrderDto orderDto) throws CreateException;
    List<OrderDto> getOrders();
    OrderDto getOrderById(Long id) throws ReadException;
    OrderDto updateOrder(OrderDto orderDto) throws ReadException, UpdateException;
    void deleteOrderById(Long id) throws DeleteException;
    OrderDto partiallyUpdateOrder(Long id, Map<String, Object> fields)
            throws ReadException, UpdateException;
}
