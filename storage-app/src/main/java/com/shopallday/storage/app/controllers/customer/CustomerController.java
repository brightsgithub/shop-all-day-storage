package com.shopallday.storage.app.controllers.customer;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.app.services.customer.CustomerService;
import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.models.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final Mapper<Customer, CustomerDto> customerMapper;

    public CustomerController(
            CustomerService customerService,
            @Qualifier("customerMapper") Mapper<Customer, CustomerDto> customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping(path = "/customers")
    public CustomerDto createCustomer(@RequestBody final CustomerDto customerDto) {
        try {
            final Customer customer = customerMapper.mapFromDtoToDomain(customerDto);
            return customerService.createCustomer(customer);
        } catch (CreateCustomerException createCustomerException) {
            createCustomerException.printStackTrace();
            return null; // return error
        }
    }
}
