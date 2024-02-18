package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoParam;

import java.util.List;

public class GetAllCustomerShippingAddressUseCase implements UseCaseNoParam<List<CustomerShippingAddress>> {

    private final CustomerShippingAddRepository customerShippingAddRepository;

    public GetAllCustomerShippingAddressUseCase(
            CustomerShippingAddRepository customerShippingAddRepository) {
        this.customerShippingAddRepository = customerShippingAddRepository;
    }

    @Override
    public List<CustomerShippingAddress> execute() throws Exception {
        return customerShippingAddRepository.getCustomerShippingAddresses();
    }
}
