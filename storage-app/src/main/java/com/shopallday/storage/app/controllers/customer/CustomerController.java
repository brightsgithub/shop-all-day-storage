package com.shopallday.storage.app.controllers.customer;

import com.shopallday.storage.app.controllers.CustomErrorResponse;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.app.services.customer.CustomerService;
import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.models.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final Mapper<Customer, CustomerDto> customerMapper;

    public CustomerController(
            CustomerService customerService,
            @Qualifier("customerMapper") Mapper<Customer, CustomerDto> customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody final CustomerDto customerDto) {
        final Customer customer = customerMapper.mapFromDtoToDomain(customerDto);
        try {
            return new ResponseEntity(customerService.createCustomer(customer), HttpStatus.CREATED);
        } catch (CreateCustomerException e) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(e);
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getCustomerById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
        } catch (ReadCustomerException rce) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(rce);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
