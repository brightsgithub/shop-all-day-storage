package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

import java.util.List;

public class CreateCustomerShippingAddressUseCase implements UseCaseNoReturnVal<List<CustomerShippingAddress>> {
    private final CustomerShippingAddRepository customerShippingAddRepository;
    private final RepositoryManager repositoryManager;

    public CreateCustomerShippingAddressUseCase(
            CustomerShippingAddRepository customerShippingAddRepository, RepositoryManager repositoryManager) {
        this.customerShippingAddRepository = customerShippingAddRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public void execute(List<CustomerShippingAddress> customerShippingAddresses) throws Exception {
        customerShippingAddRepository.createCustomerShippingAddress(customerShippingAddresses, repositoryManager);
    }
}
