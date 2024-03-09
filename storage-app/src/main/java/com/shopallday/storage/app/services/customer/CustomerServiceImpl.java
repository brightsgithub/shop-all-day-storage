package com.shopallday.storage.app.services.customer;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.app.services.BaseService;
import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.exceptions.customer.UpdateCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.usecases.customer.CreateSingleCustomerUseCase;
import com.shopallday.storage.domain.usecases.customer.GetAllCustomersUseCase;
import com.shopallday.storage.domain.usecases.customer.GetCustomersByIdUseCase;
import com.shopallday.storage.domain.usecases.customer.UpdateCustomerUseCase;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl extends BaseService implements CustomerService {

    private CreateSingleCustomerUseCase createSingleCustomerUseCase;
    private GetAllCustomersUseCase getAllCustomersUseCase;
    private GetCustomersByIdUseCase getCustomersByIdUseCase;
    private UpdateCustomerUseCase updateCustomerUseCase;
    @Qualifier("customerMapper")
    private Mapper<Customer, CustomerDto> customerMapper;

    public CustomerServiceImpl(
            CreateSingleCustomerUseCase createSingleCustomerUseCase,
            GetAllCustomersUseCase getAllCustomersUseCase,
            GetCustomersByIdUseCase getCustomersByIdUseCase,
            UpdateCustomerUseCase updateCustomerUseCase, @Qualifier("customerMapper") Mapper<Customer, CustomerDto> customerMapper) {
        this.createSingleCustomerUseCase = createSingleCustomerUseCase;
        this.getAllCustomersUseCase = getAllCustomersUseCase;
        this.getCustomersByIdUseCase = getCustomersByIdUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(Customer customer) throws CreateCustomerException {
        final Customer justCreatedCustomer = createSingleCustomerUseCase.execute(customer);
        return customerMapper.mapFromDomainToDto(justCreatedCustomer);
    }

    @Override
    @Transactional
    public CustomerDto getCustomerById(Long id) throws ReadCustomerException {
        final Customer customer = getCustomersByIdUseCase.execute(List.of(id)).get(0);
        return customerMapper.mapFromDomainToDto(customer);
    }

    @Override
    @Transactional
    public List<CustomerDto> getCustomersById(List<Long> ids) throws ReadCustomerException {
        final List<Customer> customers = getCustomersByIdUseCase.execute(ids);
        return customerMapper.mapFromDomainToDto(customers);
    }

    @Override
    @Transactional
    public List<CustomerDto> getAllCustomers() {
        final List<Customer> customers = getAllCustomersUseCase.execute();
        return customerMapper.mapFromDomainToDto(customers);
    }

    @Override
    public CustomerDto updateCustomer(Customer customer) throws ReadCustomerException, UpdateCustomerException {
        final Customer updatedCustomer = updateCustomerUseCase.execute(customer);
        return customerMapper.mapFromDomainToDto(updatedCustomer);
    }

    @Override
    public CustomerDto partiallyUpdateCustomer(
            Long id,
            Map<String, Object> fields) throws ReadCustomerException, UpdateCustomerException {
        final Customer existingCustomer = getCustomersByIdUseCase.execute(List.of(id)).get(0);
        updateFieldsOnObject(fields, existingCustomer, Customer.class);
        return updateCustomer(existingCustomer);
    }
}
