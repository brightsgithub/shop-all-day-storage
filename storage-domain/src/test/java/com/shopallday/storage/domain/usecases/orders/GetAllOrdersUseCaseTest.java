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
public class GetAllOrdersUseCaseTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    List<Order> orders;

    @InjectMocks
    private GetAllOrdersUseCase cut;

    @Test
    public void testThatGetAllOrdersWasCalledOnRepo() throws Exception {

        when(ordersRepository.getAllOrders()).thenReturn(orders);

        List<Order> actualOrders = cut.execute();
        verify(ordersRepository).getAllOrders();
        assertEquals(orders, actualOrders);
    }
}
