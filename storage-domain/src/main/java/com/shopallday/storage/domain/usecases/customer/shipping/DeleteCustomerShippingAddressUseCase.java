package com.shopallday.storage.domain.usecases.customer.shipping;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

public class DeleteCustomerShippingAddressUseCase implements UseCaseNoReturnVal<Long> {

    private final CustomerShippingAddRepository repository;

    public DeleteCustomerShippingAddressUseCase(
            CustomerShippingAddRepository repository
    ) {
        this.repository = repository;
    }


    @Override
    public void execute(Long id) throws DeleteException {
        try {
            repository.deleteCustomerShippingAddress(id);
        } catch (Exception exception) {
            throw new DeleteException("Customer shipping address with id "+ id +" could not be deleted",
                    BusinessErrorCodes.CUSTOMER_SHIPPING_COULD_NOT_BE_DELETED);
        }
    }
}
