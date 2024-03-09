package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.exceptions.customer.UpdateCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class UpdateCustomerUseCase implements UseCase<Customer, Customer> {

    private final CustomerRepository customerRepository;
    private final RepositoryManager repositoryManager;

    public UpdateCustomerUseCase(
            CustomerRepository customerRepository,
            RepositoryManager repositoryManager
    ) {
        this.customerRepository = customerRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public Customer execute(Customer customer) throws UpdateCustomerException, ReadCustomerException {
        try {
            final Long id = customer.getCustomerId();
            if(id == null) {
                throw new ReadCustomerException("null");
            }

            if(!customerRepository.isExists(id)) {
                throw new ReadCustomerException(id.toString());
            }
            return customerRepository.updateCustomer(customer);
        } catch(ReadCustomerException e) {
            throw e;
        } catch (Exception e) {
            throw new UpdateCustomerException("Customer with id "+ customer.getCustomerId()+" could not be updated");
        }
    }
}
