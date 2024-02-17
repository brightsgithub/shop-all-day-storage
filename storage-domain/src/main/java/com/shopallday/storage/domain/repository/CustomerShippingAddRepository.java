package com.shopallday.storage.domain.repository;

import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.models.CustomerShippingAddress;

import java.util.List;

public interface CustomerShippingAddRepository {
    List<CustomerShippingAddress> findCustomerShippingAddressesById(final Long id);

    List<CustomerShippingAddress> getCustomerShippingAddresses() throws ReadCustomerException;

    void createCustomerShippingAddress(final List<CustomerShippingAddress> shippingAddress, RepositoryManager repositoryManager);

    void updateCustomerShippingAddress(final CustomerShippingAddress shippingAddress, RepositoryManager repositoryManager);

    void deleteCustomerShippingAddress(final CustomerShippingAddress shippingAddress);
}
