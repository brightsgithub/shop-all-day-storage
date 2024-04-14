package com.shopallday.storage.domain.usecases.customer.shipping;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class CreateSingleCustomerShippingAddUseCase implements UseCase<CustomerShippingAddress, CustomerShippingAddress> {
    private final CustomerShippingAddRepository customerShippingAddRepository;
    private final RepositoryManager repositoryManager;

    public CreateSingleCustomerShippingAddUseCase(
            CustomerShippingAddRepository customerShippingAddRepository,
            RepositoryManager repositoryManager) {
        this.customerShippingAddRepository = customerShippingAddRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public CustomerShippingAddress execute(CustomerShippingAddress param) throws CreateException {
        try {
            final CustomerShippingAddress address = customerShippingAddRepository.createCustomerShippingAddress(
                    param, repositoryManager);

            if (address == null) {
                throw new CreateException("Could not create CustomerShippingAddress", BusinessErrorCodes.CUSTOMER_SHIPPING_COULD_NOT_BE_CREATED);
            }
            return address;
        } catch (Exception e) {
            throw new CreateException("Could not create CustomerShippingAddress", BusinessErrorCodes.CUSTOMER_SHIPPING_COULD_NOT_BE_CREATED);
        }
    }
}
