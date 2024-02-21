package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class CreateSingleCustomerUseCase implements UseCase<Customer,Customer> {

    private final CustomerRepository customerRepository;

    public CreateSingleCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer execute(Customer customer) throws CreateCustomerException {
        if (customerRepository.doesCustomerExist(customer.getUsername(), customer.getEmail())) {
            throw new CreateCustomerException("Customer already exists", BusinessErrorCodes.CUSTOMER_ALREADY_EXISTS);
        }
        return customerRepository.createCustomer(customer);
    }
}
