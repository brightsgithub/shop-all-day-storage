package com.shopallday.storage.domain.usecases.customer.shipping;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteCustomerShippingAddressUseCaseTest {

    @Mock
    private CustomerShippingAddRepository repository;

    @InjectMocks
    private DeleteCustomerShippingAddressUseCase deleteCustomerShippingAddressUseCase;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testExecute_Success() throws DeleteException {
        // Mock data
        Long id = 1L;

        // Execute the method
        deleteCustomerShippingAddressUseCase.execute(id);

        // Verify behavior
        verify(repository, times(1)).deleteCustomerShippingAddress(id);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testExecute_Exception() {
        // Mock data
        Long id = 1L;

        // Mock behavior to throw an exception
        doThrow(new RuntimeException()).when(repository).deleteCustomerShippingAddress(id);

        // Execute the method and assert the exception
        DeleteException exception = assertThrows(DeleteException.class, () -> deleteCustomerShippingAddressUseCase.execute(id));

        // Verify behavior
        verify(repository, times(1)).deleteCustomerShippingAddress(id);
        verifyNoMoreInteractions(repository);

        // Assertions
        assert exception != null;
        assert exception.getMessage().contains("Customer shipping address with id 1 could not be deleted");
        assert exception.getErrorCode() == BusinessErrorCodes.CUSTOMER_SHIPPING_COULD_NOT_BE_DELETED;
    }
}

