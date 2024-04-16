package com.shopallday.storage.domain.usecases.customer.shipping;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateSingleCustomerShippingAddUseCaseTest {

    @Mock
    private CustomerShippingAddRepository customerShippingAddRepository;

    @Mock
    private RepositoryManager repositoryManager;

    @InjectMocks
    private CreateSingleCustomerShippingAddUseCase createSingleCustomerShippingAddUseCase;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testExecute_Success() throws CreateException {
        // Mock data
        CustomerShippingAddress inputAddress = new CustomerShippingAddress(null, null, "Address", "Address_2", "City", "PostCode");
        CustomerShippingAddress expectedAddress = new CustomerShippingAddress(1L, null, "Address", "Address_2", "City", "PostCode");

        // Mock behavior
        when(customerShippingAddRepository.createCustomerShippingAddress(inputAddress, repositoryManager)).thenReturn(expectedAddress);

        // Execute the method
        CustomerShippingAddress result = createSingleCustomerShippingAddUseCase.execute(inputAddress);

        // Verify behavior
        verify(customerShippingAddRepository, times(1)).createCustomerShippingAddress(inputAddress, repositoryManager);
        verifyNoMoreInteractions(customerShippingAddRepository);

        // Assertions
        assertNotNull(result);
        assertEquals(1L, result.getShippingAddressId()); // Assuming the ID is set to 1
    }

    @Test
    public void testExecute_Failure() {
        // Mock data
        CustomerShippingAddress inputAddress = new CustomerShippingAddress(null, null, "Address", "Address_2", "City", "PostCode");

        // Mock behavior to return null, indicating failure
        when(customerShippingAddRepository.createCustomerShippingAddress(inputAddress, repositoryManager)).thenReturn(null);

        // Execute the method and assert the exception
        CreateException exception = assertThrows(CreateException.class, () -> createSingleCustomerShippingAddUseCase.execute(inputAddress));

        // Verify behavior
        verify(customerShippingAddRepository, times(1)).createCustomerShippingAddress(inputAddress, repositoryManager);
        verifyNoMoreInteractions(customerShippingAddRepository);

        // Assertions
        assertEquals("Could not create CustomerShippingAddress", exception.getMessage());
        assertEquals(BusinessErrorCodes.CUSTOMER_SHIPPING_COULD_NOT_BE_CREATED, exception.getErrorCode());
    }

    @Test
    public void testExecute_Exception() {
        // Mock data
        CustomerShippingAddress inputAddress = new CustomerShippingAddress(null, null, "Address", "Address_2", "City", "PostCode");

        // Mock behavior to throw an exception
        when(customerShippingAddRepository.createCustomerShippingAddress(inputAddress, repositoryManager)).thenThrow(new RuntimeException());

        // Execute the method and assert the exception
        CreateException exception = assertThrows(CreateException.class, () -> createSingleCustomerShippingAddUseCase.execute(inputAddress));

        // Verify behavior
        verify(customerShippingAddRepository, times(1)).createCustomerShippingAddress(inputAddress, repositoryManager);
        verifyNoMoreInteractions(customerShippingAddRepository);

        // Assertions
        assertEquals("Could not create CustomerShippingAddress", exception.getMessage());
        assertEquals(BusinessErrorCodes.CUSTOMER_SHIPPING_COULD_NOT_BE_CREATED, exception.getErrorCode());
    }
}
