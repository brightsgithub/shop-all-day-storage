package com.shopallday.storage.domain.repository.customer;

import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.RepositoryManager;

import java.util.List;

public interface CustomerShippingAddRepository {
    List<CustomerShippingAddress> findCustomerShippingAddressesById(final Long id);

    List<CustomerShippingAddress> getCustomerShippingAddresses();

    List<CustomerShippingAddress> createCustomerShippingAddress(final List<CustomerShippingAddress> shippingAddress, RepositoryManager repositoryManager);

    CustomerShippingAddress createCustomerShippingAddress(CustomerShippingAddress shippingAddresses, RepositoryManager repositoryManager);

    CustomerShippingAddress updateCustomerShippingAddress(final CustomerShippingAddress shippingAddress, RepositoryManager repositoryManager);

    void deleteCustomerShippingAddress(final Long id);

    void deleteAddressById(Long id);

    boolean isExists(Long id);
}
