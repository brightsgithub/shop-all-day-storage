package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.CustomerRepository;
import com.shopallday.storage.domain.usecases.TestFactoryData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllCustomersUseCaseTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private GetAllCustomersUseCase getAllCustomersUseCase;

    @Test
    public void testExecute() throws ReadCustomerException {
        // Arrange
        List<Customer> expectedCustomers = TestFactoryData.createMockCustomers(2);

        // Mocking behavior of the customerRepository
        when(customerRepository.getCustomers()).thenReturn(expectedCustomers);

        // Act
        List<Customer> actualCustomers = getAllCustomersUseCase.execute();

        // Assert
        assertEquals(expectedCustomers.size(), actualCustomers.size());
        for (int i = 0; i < expectedCustomers.size(); i++) {
            Customer expectedCustomer = expectedCustomers.get(i);
            Customer actualCustomer = actualCustomers.get(i);
            assertEquals(expectedCustomer.getCustomerId(), actualCustomer.getCustomerId());
            assertEquals(expectedCustomer.getFirstName(), actualCustomer.getFirstName());
            assertEquals(expectedCustomer.getLastName(), actualCustomer.getLastName());
            assertEquals(expectedCustomer.getAddress1(), actualCustomer.getAddress1());
            assertEquals(expectedCustomer.getAddress2(), actualCustomer.getAddress2());
            assertEquals(expectedCustomer.getCity(), actualCustomer.getCity());
            assertEquals(expectedCustomer.getPostCode(), actualCustomer.getPostCode());
            assertEquals(expectedCustomer.getUsername(), actualCustomer.getUsername());
            assertEquals(expectedCustomer.getPassword(), actualCustomer.getPassword());
            assertEquals(expectedCustomer.getEnabled(), actualCustomer.getEnabled());
            assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail());
            assertEquals(expectedCustomer.getPhoneNumber(), actualCustomer.getPhoneNumber());
        }
    }
}

