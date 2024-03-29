package com.shopallday.storage.infra.repository.orders;

import com.shopallday.storage.domain.models.OrderStatusType;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.repository.BaseIntegrationTests;
import com.shopallday.storage.infra.repository.TestFactoryData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderStatusTypeRepositoryIntegrationTests extends BaseIntegrationTests {

    private OrderStatusTypeRepository orderStatusTypeRepository;
    private RepositoryManager repositoryManager;

    @Autowired
    public OrderStatusTypeRepositoryIntegrationTests(OrderStatusTypeRepository orderStatusTypeRepository, RepositoryManager repositoryManager) {
        this.orderStatusTypeRepository = orderStatusTypeRepository;
        this.repositoryManager = repositoryManager;
    }

    @Test
    @Transactional
    public void testThatOrderStatusTypeCanBeCreatedAndObtained() {
        final List<OrderStatusType> expectedOrderStatusTypes = TestFactoryData.createMockOrderStatusType(3);
        orderStatusTypeRepository.createOrderStatusType(expectedOrderStatusTypes);

        List<OrderStatusType> actualOrderStatusTypes = orderStatusTypeRepository.getAllOrderStatusTypes();

        assertEquals(expectedOrderStatusTypes.size(), actualOrderStatusTypes.size());

        for (int i = 0; i < expectedOrderStatusTypes.size(); i++) {
            OrderStatusType expectedOrderStatusType = expectedOrderStatusTypes.get(i);
            OrderStatusType actualOrderStatusType = actualOrderStatusTypes.get(i);
            assertEquals(expectedOrderStatusType.getStatus(), actualOrderStatusType.getStatus());
        }
    }
}
