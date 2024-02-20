package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.CustomerOrderDetail;
import com.shopallday.storage.domain.repository.orders.CustomerOrderDetailRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

public class GetCustomerOrderDetailUseCase implements UseCase<List<CustomerOrderDetail>, Long> {

    private final CustomerOrderDetailRepository customerOrderDetailRepository;

    public GetCustomerOrderDetailUseCase(CustomerOrderDetailRepository customerOrderDetailRepository) {
        this.customerOrderDetailRepository = customerOrderDetailRepository;
    }

    @Override
    public List<CustomerOrderDetail> execute(Long customerId) throws Exception {
        return customerOrderDetailRepository.getOrdersByCustomerId(customerId);
    }
}
