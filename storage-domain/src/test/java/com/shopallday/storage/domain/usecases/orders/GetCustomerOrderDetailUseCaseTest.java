package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.CustomerOrderDetail;
import com.shopallday.storage.domain.repository.orders.CustomerOrderDetailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCustomerOrderDetailUseCaseTest {

    @Mock
    private CustomerOrderDetailRepository customerOrderDetailRepository;

    @Mock
    private List<CustomerOrderDetail> customerOrderDetails;

    @InjectMocks
    private GetCustomerOrderDetailUseCase cut;

    @Test
    public void testThatGetOrdersByCustomerIdWasCalledOnRepo() throws Exception {
        final Long id = 1L;
        when(customerOrderDetailRepository.getOrdersByCustomerId(id)).thenReturn(customerOrderDetails);

        List<CustomerOrderDetail> actual = cut.execute(id);
        verify(customerOrderDetailRepository).getOrdersByCustomerId(id);
        assertEquals(customerOrderDetails, actual);
    }
}
