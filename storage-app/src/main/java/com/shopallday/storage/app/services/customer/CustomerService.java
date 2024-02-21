package com.shopallday.storage.app.services.customer;

import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.models.Customer;
import org.springframework.stereotype.Component;

@Component
public interface CustomerService {
    CustomerDto createCustomer(Customer customer) throws CreateCustomerException;
}
