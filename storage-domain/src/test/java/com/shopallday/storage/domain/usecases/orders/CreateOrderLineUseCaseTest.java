package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.usecases.TestFactoryData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateOrderLineUseCaseTest {

    @Mock
    private OrderLinesRepository orderLinesRepository;
    @Mock
    private RepositoryManager repositoryManager;

    @Mock
    List<OrderLine> orderLines;

    @InjectMocks
    private CreateOrderLineUseCase cut;

    @Test
    public void testThatCreateOrderLineWasCalledOnRepo() throws Exception {
        cut.execute(orderLines);
        verify(orderLinesRepository).createOrderLine(orderLines, repositoryManager);
    }

    @Test
    public void testExecute_Success() throws Exception {
        // Mock data
        List<OrderLine> orderLines = TestFactoryData.createMockOrderLines(1);

        // Execute the method
        cut.execute(orderLines);

        // Verify behavior
        verify(orderLinesRepository, times(1)).createOrderLine(orderLines, repositoryManager);
        verifyNoMoreInteractions(orderLinesRepository);
    }
}
