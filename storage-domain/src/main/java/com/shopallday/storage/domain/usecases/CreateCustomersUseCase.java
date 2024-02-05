package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.CustomerRepository;

import java.util.List;

public class CreateCustomersUseCase {

    private final CustomerRepository customerRepository;

    public CreateCustomersUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(List<Customer> customers) throws CreateCustomerException {
        customerRepository.createCustomers(customers);
    }
}
