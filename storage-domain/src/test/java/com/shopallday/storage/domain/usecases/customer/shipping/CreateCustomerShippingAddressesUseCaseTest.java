package com.shopallday.storage.domain.usecases.customer.shipping;

import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.usecases.TestFactoryData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerShippingAddressesUseCaseTest {

    @Mock
    private CustomerShippingAddRepository customerShippingAddRepository;

    @Mock
    private RepositoryManager repositoryManager;

    @InjectMocks
    private CreateCustomerShippingAddressesUseCase createCustomerShippingAddressesUseCase;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testExecute_Success() throws CreateException {
        // Mock data
        List<CustomerShippingAddress> inputAddresses = TestFactoryData.createMockCustomerShippingAddresses(5);
        List<CustomerShippingAddress> expectedAddresses = new ArrayList<>(inputAddresses);

        // Mock behavior
        when(customerShippingAddRepository.createCustomerShippingAddress(inputAddresses, repositoryManager)).thenReturn(expectedAddresses);

        // Execute the method
        List<CustomerShippingAddress> result = createCustomerShippingAddressesUseCase.execute(inputAddresses);

        // Verify behavior
        verify(customerShippingAddRepository, times(1)).createCustomerShippingAddress(inputAddresses, repositoryManager);
        verifyNoMoreInteractions(customerShippingAddRepository);

        // Assertions
        assert result != null;
        assert result.size() == 5; // Assuming 5 addresses were created
    }
}

