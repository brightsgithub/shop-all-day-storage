package com.shopallday.storage.domain.usecases.customer.shipping;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.*;

public class UpdateCustomerShippingAddressUseCase implements UseCase<CustomerShippingAddress, CustomerShippingAddress> {
    private CustomerShippingAddRepository repository;
    private RepositoryManager repositoryManager;

    public UpdateCustomerShippingAddressUseCase(
            CustomerShippingAddRepository repository,
            RepositoryManager repositoryManager) {
        this.repository = repository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public CustomerShippingAddress execute(CustomerShippingAddress shippingAddress) throws ReadException, UpdateException {
        try {
            final Long id = shippingAddress.getShippingAddressId();
            if (id == null || !repository.isExists(id)) {
                throw new ReadException("Cannot find CustomerShippingAddress with id "+id, CUSTOMER_SHIPPING_NOT_FOUND);
            }

            return repository.updateCustomerShippingAddress(shippingAddress, repositoryManager);
        } catch (ReadException e) {
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new UpdateException("ShippingAddress with id "+shippingAddress.getShippingAddressId()+" could not be updated",
                    ORDER_COULD_NOT_BE_UPDATED);
        }
    }
}
