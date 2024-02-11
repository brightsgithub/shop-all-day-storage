package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.CustomerRepository;

import java.util.List;

public class GetAllCustomersUseCase implements UseCaseNoParam<List<Customer>> {

    private final CustomerRepository customerRepository;

    public GetAllCustomersUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> execute() throws ReadCustomerException {
        return customerRepository.getCustomers();
    }
}
