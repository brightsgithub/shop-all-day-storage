package com.shopallday.storage.domain.repository.orders;

import com.shopallday.storage.domain.models.CustomerOrderDetail;

import java.util.List;

public interface CustomerOrderDetailRepository {

    List<CustomerOrderDetail> getOrdersByCustomerId(Long customerId);
}
