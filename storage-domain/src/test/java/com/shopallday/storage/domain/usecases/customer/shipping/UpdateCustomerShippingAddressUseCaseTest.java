package com.shopallday.storage.domain.usecases.customer.shipping;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.CUSTOMER_SHIPPING_NOT_FOUND;
import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.ORDER_COULD_NOT_BE_UPDATED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateCustomerShippingAddressUseCaseTest {

    @Mock
    private CustomerShippingAddRepository repository;

    @Mock
    private RepositoryManager repositoryManager;

    @InjectMocks
    private UpdateCustomerShippingAddressUseCase updateCustomerShippingAddressUseCase;

    @BeforeEach
    public void setUp() {
        // You can perform any setup here if needed
    }

    @Test
    public void testExecute_Success() throws ReadException, UpdateException {
        // Mock data
        CustomerShippingAddress shippingAddress = new CustomerShippingAddress(1L, null, "Address", "Address_2", "City", "PostCode");

        // Mock behavior
        when(repository.isExists(shippingAddress.getShippingAddressId())).thenReturn(true);
        when(repository.updateCustomerShippingAddress(shippingAddress, repositoryManager)).thenReturn(shippingAddress);

        // Execute the method
        CustomerShippingAddress result = updateCustomerShippingAddressUseCase.execute(shippingAddress);

        // Verify behavior
        verify(repository, times(1)).isExists(shippingAddress.getShippingAddressId());
        verify(repository, times(1)).updateCustomerShippingAddress(shippingAddress, repositoryManager);
        verifyNoMoreInteractions(repository);

        // Assertions
        assertEquals(shippingAddress, result);
    }

    @Test
    public void testExecute_ShippingAddressNotFound() {
        // Mock data
        CustomerShippingAddress shippingAddress = new CustomerShippingAddress(1L, null, "Address", "Address_2", "City", "PostCode");

        // Mock behavior
        when(repository.isExists(shippingAddress.getShippingAddressId())).thenReturn(false);

        // Execute the method and assert the exception
        ReadException exception = assertThrows(ReadException.class, () -> updateCustomerShippingAddressUseCase.execute(shippingAddress));

        // Verify behavior
        verify(repository, times(1)).isExists(shippingAddress.getShippingAddressId());
        verifyNoMoreInteractions(repository);

        // Assertions
        assertEquals("Cannot find CustomerShippingAddress with id 1", exception.getMessage());
        assertEquals(CUSTOMER_SHIPPING_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    public void testExecute_UpdateException() {
        // Mock data
        CustomerShippingAddress shippingAddress = new CustomerShippingAddress(1L, null, "Address", "Address_2", "City", "PostCode");

        // Mock behavior
        when(repository.isExists(shippingAddress.getShippingAddressId())).thenReturn(true);
        when(repository.updateCustomerShippingAddress(shippingAddress, repositoryManager)).thenThrow(new RuntimeException());

        // Execute the method and assert the exception
        UpdateException exception = assertThrows(UpdateException.class, () -> updateCustomerShippingAddressUseCase.execute(shippingAddress));

        // Verify behavior
        verify(repository, times(1)).isExists(shippingAddress.getShippingAddressId());
        verify(repository, times(1)).updateCustomerShippingAddress(shippingAddress, repositoryManager);
        verifyNoMoreInteractions(repository);

        // Assertions
        assertEquals("ShippingAddress with id 1 could not be updated", exception.getMessage());
        assertEquals(ORDER_COULD_NOT_BE_UPDATED, exception.getErrorCode());
    }
}

