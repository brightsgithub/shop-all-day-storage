package com.shopallday.storage.domain.usecases.customer;

import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.usecases.TestFactoryData;
import com.shopallday.storage.domain.usecases.customer.shipping.GetCustomerShipAddByIdUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCustomerShipAddByIdUseCaseTest {
    @Mock
    private CustomerShippingAddRepository customerShippingAddRepository;
    @InjectMocks
    private GetCustomerShipAddByIdUseCase cut;

    @Test
    public void testThatFindCustomerShippingAddressesByIdWasCalledOnRepo() throws Exception {
        List<CustomerShippingAddress> expectedShippingAddresses = TestFactoryData.createMockCustomerShippingAddresses(3);
        Long customerId = 1L;
        when(customerShippingAddRepository.findCustomerShippingAddressesById(customerId)).thenReturn(expectedShippingAddresses);

        List<CustomerShippingAddress> actualShippingAddresses = cut.execute(customerId);

        assertEquals(expectedShippingAddresses.size(), actualShippingAddresses.size());

        for (int i=0; i<expectedShippingAddresses.size(); i++) {
            CustomerShippingAddress expected = expectedShippingAddresses.get(i);
            CustomerShippingAddress actual = actualShippingAddresses.get(i);

            assertEquals(expected.getAddress1(), actual.getAddress1());
            assertEquals(expected.getAddress2(), actual.getAddress2());
            assertEquals(expected.getCustomer(), actual.getCustomer());
        }
    }
}
