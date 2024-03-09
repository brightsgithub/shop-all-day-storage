package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.exceptions.customer.DeleteCustomerException;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

public class DeleteCustomerUseCase implements UseCaseNoReturnVal<Long> {

    private final CustomerRepository customerRepository;

    public DeleteCustomerUseCase(
            CustomerRepository customerRepository
    ) {
        this.customerRepository = customerRepository;
    }


    @Override
    public void execute(Long id) throws DeleteCustomerException{
        try {
            customerRepository.deleteCustomerById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new DeleteCustomerException("Customer with id "+ id +" could not be deleted");
        }
    }
}
