package com.shopallday.storage.domain.usecases.orders;

import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

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
}
