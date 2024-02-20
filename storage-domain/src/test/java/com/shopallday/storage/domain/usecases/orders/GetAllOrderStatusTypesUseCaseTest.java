package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
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
public class GetAllOrderStatusTypesUseCaseTest {
    @Mock
    private OrderStatusTypeRepository orderStatusTypeRepository;

    @Mock
    List<OrderStatusType> orderStatusTypes;

    @InjectMocks
    private GetAllOrderStatusTypesUseCase cut;

    @Test
    public void testThatGetAllOrdersWasCalledOnRepo() throws Exception {

        when(orderStatusTypeRepository.getAllOrderStatusTypes()).thenReturn(orderStatusTypes);

        List<OrderStatusType> actualStatusTypes = cut.execute();
        verify(orderStatusTypeRepository).getAllOrderStatusTypes();
        assertEquals(orderStatusTypes, actualStatusTypes);
    }
}
