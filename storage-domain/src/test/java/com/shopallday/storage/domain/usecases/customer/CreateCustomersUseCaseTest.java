package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateCustomersUseCaseTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    List<Customer> customerList;

    @InjectMocks
    private CreateCustomersUseCase underTest;

    @Test
    public void testThatCreateCustomersWasCalledOnRepo() throws Exception{
        underTest.execute(customerList);

        verify(customerRepository).createCustomers(customerList);
    }
}
