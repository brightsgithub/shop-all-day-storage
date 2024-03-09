package com.shopallday.storage.app.services.customer;

import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.exceptions.customer.DeleteCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.exceptions.customer.UpdateCustomerException;
import com.shopallday.storage.domain.models.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CustomerService {
    CustomerDto createCustomer(Customer customer) throws CreateCustomerException;
    CustomerDto getCustomerById(Long id) throws ReadCustomerException;
    List<CustomerDto> getCustomersById(List<Long> ids) throws ReadCustomerException;
    List<CustomerDto> getAllCustomers();
    CustomerDto updateCustomer(Customer customer) throws ReadCustomerException, UpdateCustomerException;
    CustomerDto partiallyUpdateCustomer(final Long id, final Map<String, Object> fields) throws ReadCustomerException, UpdateCustomerException;
    void deleteCustomerById(Long id) throws DeleteCustomerException;
}
