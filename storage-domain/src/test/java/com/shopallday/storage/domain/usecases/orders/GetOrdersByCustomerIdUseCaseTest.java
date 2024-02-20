package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
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
public class GetOrdersByCustomerIdUseCaseTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    List<Order> orders;

    @InjectMocks
    private GetOrdersByCustomerIdUseCase cut;

    @Test
    public void testThatFindOrdersByIdWasCalledOnRepo() throws Exception {

        List<Long> ids = List.of(1L);

        when(ordersRepository.findOrdersById(ids)).thenReturn(orders);

        List<Order> actualOrders = cut.execute(ids);

        verify(ordersRepository).findOrdersById(ids);
        assertEquals(orders, actualOrders);
    }
}
