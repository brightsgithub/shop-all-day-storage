package com.shopallday.storage.domain.usecases.customer.shipping;

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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetAllCustomerShippingAddressUseCaseTest {

    @Mock
    private CustomerShippingAddRepository customerShippingAddRepository;

    @InjectMocks
    private GetAllCustomerShippingAddressUseCase getAllCustomerShippingAddressUseCase;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testExecute_Success() {
        // Mock data
        List<CustomerShippingAddress> expectedAddresses = new ArrayList<>();
        expectedAddresses.add(new CustomerShippingAddress(1L, null, "Address1", "Address_2", "City", "PostCode"));
        expectedAddresses.add(new CustomerShippingAddress(2L, null, "Address2", "Address_2", "City", "PostCode"));

        // Mock behavior
        when(customerShippingAddRepository.getCustomerShippingAddresses()).thenReturn(expectedAddresses);

        // Execute the method
        List<CustomerShippingAddress> result = getAllCustomerShippingAddressUseCase.execute();

        // Verify behavior
        verify(customerShippingAddRepository, times(1)).getCustomerShippingAddresses();
        verifyNoMoreInteractions(customerShippingAddRepository);

        // Assertions
        assertEquals(expectedAddresses.size(), result.size());
        assertEquals(expectedAddresses.get(0), result.get(0));
        assertEquals(expectedAddresses.get(1), result.get(1));
    }
}

