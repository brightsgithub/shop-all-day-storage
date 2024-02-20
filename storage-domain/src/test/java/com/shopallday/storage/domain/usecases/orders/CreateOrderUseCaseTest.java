package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateOrderUseCaseTest {

    @Mock
    private OrdersRepository ordersRepository;
    @Mock
    private RepositoryManager repositoryManager;

    @Mock
    List<Order> orders;

    @InjectMocks
    private CreateOrderUseCase cut;

    @Test
    public void testThatCreateOrderWasCalledOnRepo() throws Exception {
        cut.execute(orders);
        verify(ordersRepository).createOrder(orders, repositoryManager);
    }
}
