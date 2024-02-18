package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

import java.util.List;

public class CreateCustomersUseCase implements UseCaseNoReturnVal<List<Customer>> {

    private final CustomerRepository customerRepository;

    public CreateCustomersUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(List<Customer> customers) throws CreateCustomerException {
        customerRepository.createCustomers(customers);
    }
}
