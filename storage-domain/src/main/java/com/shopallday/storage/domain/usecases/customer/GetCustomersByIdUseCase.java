package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

public class GetCustomersByIdUseCase implements UseCase<List<Customer>, List<Long>> {

    private final CustomerRepository customerRepository;

    public GetCustomersByIdUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> execute(List<Long> ids) throws ReadCustomerException {
        final List<Customer> customers = customerRepository.findCustomersById(ids);
        if (customers.isEmpty()) {
            throw new ReadCustomerException(ids.toString());
        }
        return customers;
    }
}
