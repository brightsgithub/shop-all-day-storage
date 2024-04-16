package com.shopallday.storage.domain.usecases.customer.shipping;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetCustomerShipAddByIdUseCaseTest {

    @Mock
    private CustomerShippingAddRepository customerShippingAddRepository;

    @InjectMocks
    private GetCustomerShipAddByIdUseCase getCustomerShipAddByIdUseCase;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testExecute_Success() throws ReadException {
        // Mock data
        Long id = 1L;
        List<CustomerShippingAddress> expectedAddress = new ArrayList<>();
        expectedAddress.add(new CustomerShippingAddress(id, null, "Address", "Address_2", "City", "PostCode"));

        // Mock behavior
        when(customerShippingAddRepository.findCustomerShippingAddressesById(id)).thenReturn(expectedAddress);

        // Execute the method
        List<CustomerShippingAddress> result = getCustomerShipAddByIdUseCase.execute(id);

        // Verify behavior
        verify(customerShippingAddRepository, times(1)).findCustomerShippingAddressesById(id);
        verifyNoMoreInteractions(customerShippingAddRepository);

        // Assertions
        assertEquals(expectedAddress, result);
    }

    @Test
    public void testExecute_IdIsNull() {
        // Execute the method and assert the exception
        ReadException exception = assertThrows(ReadException.class, () -> getCustomerShipAddByIdUseCase.execute(null));

        // Assertions
        assertEquals("CustomerShippingAddress id cannot be null", exception.getMessage());
        assertEquals(BusinessErrorCodes.CUSTOMER_SHIPPING_NOT_FOUND, exception.getErrorCode());
    }
}

