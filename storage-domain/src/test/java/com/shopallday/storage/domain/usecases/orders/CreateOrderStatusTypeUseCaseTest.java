package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.usecases.orderstatustype.CreateOrderStatusTypeUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateOrderStatusTypeUseCaseTest {
    @Mock
    private OrderStatusTypeRepository orderStatusTypeRepository;
    @Mock
    private RepositoryManager repositoryManager;
    @Mock
    List<OrderStatusType> orderStatusTypes;
    @InjectMocks
    private CreateOrderStatusTypeUseCase cut;

    @Test
    public void testThat() throws Exception {
        cut.execute(orderStatusTypes);
        verify(orderStatusTypeRepository).createOrderStatusType(orderStatusTypes);
    }
}
