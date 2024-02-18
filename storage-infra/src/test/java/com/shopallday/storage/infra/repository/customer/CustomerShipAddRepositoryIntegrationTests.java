package com.shopallday.storage.infra.repository.customer;

import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.repository.BaseIntegrationTests;
import com.shopallday.storage.infra.repository.TestFactoryData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerShipAddRepositoryIntegrationTests extends BaseIntegrationTests {

    private CustomerShippingAddRepository customerShippingAddRepository;
    private RepositoryManager repositoryManager;

    @Autowired
    public CustomerShipAddRepositoryIntegrationTests(CustomerShippingAddRepository customerShippingAddRepository, RepositoryManager repositoryManager) {
        this.customerShippingAddRepository = customerShippingAddRepository;
        this.repositoryManager = repositoryManager;
    }

    @Test
    @Transactional
    public void testThatCustomerShipAddCanBeCreatedAndObtained() throws Exception {
        List<CustomerShippingAddress> expectedCustomerShippingAddresses =
                TestFactoryData.createMockCustomerShippingAddresses(3);

        customerShippingAddRepository.createCustomerShippingAddress(expectedCustomerShippingAddresses, repositoryManager);

        List<CustomerShippingAddress> actualCustomerShippingAddresses =
                customerShippingAddRepository.getCustomerShippingAddresses();

        for (int i = 0; i<expectedCustomerShippingAddresses.size(); i++) {
            CustomerShippingAddress expected = expectedCustomerShippingAddresses.get(i);
            CustomerShippingAddress actual = actualCustomerShippingAddresses.get(i);

            assertEquals(expected.getAddress1(), actual.getAddress1());
            assertEquals(expected.getAddress2(), actual.getAddress2());
            assertEquals(expected.getCity(), actual.getCity());
            assertEquals(expected.getPostCode(), actual.getPostCode());
            assertEquals(expected.getCustomer().getUsername(), actual.getCustomer().getUsername());
            assertEquals(expected.getCustomer().getPassword(), actual.getCustomer().getPassword());
            assertEquals(expected.getCustomer().getEmail(), actual.getCustomer().getEmail());
        }
    }
}
