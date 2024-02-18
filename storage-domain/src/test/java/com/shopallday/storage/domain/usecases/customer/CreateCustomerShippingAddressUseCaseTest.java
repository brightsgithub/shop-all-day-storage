package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.usecases.TestFactoryData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerShippingAddressUseCaseTest {
    @Mock
    private CustomerShippingAddRepository customerShippingAddRepository;
    @Mock
    private RepositoryManager repositoryManager;
    @InjectMocks
    private CreateCustomerShippingAddressUseCase cut;

    @Test
    public void testThatGetCreateCustomerShippingAddressWasCalledOnRepo() throws Exception {
        List<CustomerShippingAddress> expectedShippingAddresses = TestFactoryData.createMockCustomerShippingAddresses(3);
        cut.execute(expectedShippingAddresses);
        verify(customerShippingAddRepository).createCustomerShippingAddress(expectedShippingAddresses, repositoryManager);
    }
}
