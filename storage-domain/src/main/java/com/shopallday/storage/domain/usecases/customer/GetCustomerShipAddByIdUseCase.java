package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

public class GetCustomerShipAddByIdUseCase implements UseCase<List<CustomerShippingAddress>, Long> {

    private final CustomerShippingAddRepository customerShippingAddRepository;

    public GetCustomerShipAddByIdUseCase(
            CustomerShippingAddRepository customerShippingAddRepository) {
        this.customerShippingAddRepository = customerShippingAddRepository;
    }

    @Override
    public List<CustomerShippingAddress> execute(Long id) throws Exception {
        return customerShippingAddRepository.findCustomerShippingAddressesById(id);
    }
}
