package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

public class CreateCustomersUseCase implements UseCase<List<Customer>,List<Customer>> {

    private final CustomerRepository customerRepository;

    public CreateCustomersUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> execute(List<Customer> customers) throws CreateCustomerException {
        return customerRepository.createCustomers(customers);
    }
}
