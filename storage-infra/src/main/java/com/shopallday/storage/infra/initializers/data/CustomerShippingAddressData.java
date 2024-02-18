package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerShippingAddressData implements DataHelper {

    private CustomerRepository customerRepository;
    private CustomerShippingAddRepository customerShippingAddRepository;
    private RepositoryManager repositoryManager;

    public CustomerShippingAddressData(
            CustomerRepository customerRepository,
            CustomerShippingAddRepository customerShippingAddRepository,
            RepositoryManager repositoryManager
    ) {
        this.customerRepository = customerRepository;
        this.customerShippingAddRepository = customerShippingAddRepository;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public void create() throws Exception {
        System.out.println("CustomerShippingAddressData called...");
        List<Customer> customers = customerRepository.getCustomers();
        List<CustomerShippingAddress> customerShippingAddresses = new ArrayList<>();
        Customer sameCustomer = customers.get(0);

        customerShippingAddresses.add(
                new CustomerShippingAddress(
                        null,
                        sameCustomer,
                        "21 London road",
                        "Devonshire",
                        "London",
                        "DE1 1XQ"
                )
        );

        customerShippingAddresses.add(
                new CustomerShippingAddress(
                        null,
                        sameCustomer,
                        "42 Homesdale road",
                        "Preston park",
                        "Brighton",
                        "BP2 4MP"
                )
        );

        customerShippingAddRepository.createCustomerShippingAddress(customerShippingAddresses, repositoryManager);
        System.out.println("CustomerShippingAddressData finished");
    }

    @Override
    public void print() throws Exception {

    }
}
