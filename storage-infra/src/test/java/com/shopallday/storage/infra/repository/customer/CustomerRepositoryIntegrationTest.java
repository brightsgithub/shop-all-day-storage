package com.shopallday.storage.infra.repository.customer;

import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.infra.repository.BaseIntegrationTests;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.shopallday.storage.infra.repository.TestFactoryData.createMockCustomers;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerRepositoryIntegrationTest extends BaseIntegrationTests {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerRepositoryIntegrationTest(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Test
    @Transactional
    public void testThatCustomersCanBeCreatedAndObtained() throws Exception {
        List<Customer> expectedResults = createMockCustomers(3);
        List<Customer> justCreatedCustomers = customerRepository.createCustomers(expectedResults);

        List<Customer> actualResults = customerRepository.getCustomers();

        assertEquals(actualResults.size() ,expectedResults.size());

        for (int i = 0; i < expectedResults.size(); i++) {
            Customer expectedCustomer = expectedResults.get(i);
            Customer actualCustomer = actualResults.get(i);
            Customer justCreatedCustomer = justCreatedCustomers.get(i);
            assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail());
            assertEquals(expectedCustomer.getFirstName(), actualCustomer.getFirstName());

            assertEquals(actualCustomer, justCreatedCustomer);
        }
    }
}
