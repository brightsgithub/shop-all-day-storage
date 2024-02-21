package com.shopallday.storage.app.services.customer;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.usecases.customer.CreateCustomersUseCase;
import com.shopallday.storage.domain.usecases.customer.GetAllCustomersUseCase;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CreateCustomersUseCase createCustomersUseCase;
    private GetAllCustomersUseCase getAllCustomersUseCase;
    @Qualifier("customerMapper")
    private Mapper<Customer, CustomerDto> customerMapper;

    public CustomerServiceImpl(
            CreateCustomersUseCase createCustomersUseCase,
            @Qualifier("customerMapper") Mapper<Customer, CustomerDto> customerMapper) {
        this.createCustomersUseCase = createCustomersUseCase;
        this.getAllCustomersUseCase = getAllCustomersUseCase;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(Customer customer) throws CreateCustomerException {
        final Customer justCreatedCustomer =  createCustomersUseCase.execute(List.of(customer)).get(0);
        return customerMapper.mapFromDomainToDto(justCreatedCustomer);
    }
}
