package com.shopallday.storage.domain.usecases.customer.shipping;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

public class CreateCustomerShippingAddressesUseCase implements UseCase<List<CustomerShippingAddress>, List<CustomerShippingAddress>> {
    private final CustomerShippingAddRepository customerShippingAddRepository;
    private final RepositoryManager repositoryManager;

    public CreateCustomerShippingAddressesUseCase(
            CustomerShippingAddRepository customerShippingAddRepository, RepositoryManager repositoryManager) {
        this.customerShippingAddRepository = customerShippingAddRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public List<CustomerShippingAddress> execute(List<CustomerShippingAddress> customerShippingAddresses) throws CreateException {
        try {
            return customerShippingAddRepository.createCustomerShippingAddress(
                    customerShippingAddresses, repositoryManager);
        } catch (Exception e) {
            throw new CreateException("Could not create CustomerShippingAddress", BusinessErrorCodes.CUSTOMER_SHIPPING_COULD_NOT_BE_CREATED);
        }
    }
}
