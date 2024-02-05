package com.shopallday.storage.domain.repository;

import com.shopallday.storage.domain.exceptions.customer.DeleteCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.exceptions.customer.UpdateCustomerException;
import com.shopallday.storage.domain.models.Customer;

import java.util.List;

public interface CustomerRepository {

    Customer findCustomerById(final Long id) throws ReadCustomerException;

    List<Customer> findCustomersById(final List<Long> ids) throws ReadCustomerException;

    List<Customer> getCustomers() throws ReadCustomerException;

    void createCustomer(final Customer customer) throws DeleteCustomerException;

    void updateCustomer(final Customer customer) throws UpdateCustomerException;

    void deleteCustomer(final Customer customer) throws DeleteCustomerException;

    void deleteCustomerById(final Long id) throws DeleteCustomerException;
}
