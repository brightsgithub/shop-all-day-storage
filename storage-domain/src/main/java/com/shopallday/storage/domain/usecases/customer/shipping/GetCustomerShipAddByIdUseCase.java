package com.shopallday.storage.domain.usecases.customer.shipping;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
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
    public List<CustomerShippingAddress> execute(Long id) throws ReadException {
        if (id == null) {
            throw new ReadException("CustomerShippingAddress id cannot be null", BusinessErrorCodes.CUSTOMER_SHIPPING_NOT_FOUND);
        }
        return customerShippingAddRepository.findCustomerShippingAddressesById(id);
    }
}
