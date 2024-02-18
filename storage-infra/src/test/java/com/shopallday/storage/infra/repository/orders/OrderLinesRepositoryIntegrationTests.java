package com.shopallday.storage.infra.repository.orders;

import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.repository.BaseIntegrationTests;
import com.shopallday.storage.infra.repository.TestFactoryData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderLinesRepositoryIntegrationTests extends BaseIntegrationTests {

    private OrderLinesRepository orderLinesRepository;
    private RepositoryManager repositoryManager;

    @Autowired
    public OrderLinesRepositoryIntegrationTests(
            OrderLinesRepository orderLinesRepository,
            RepositoryManager repositoryManager) {
        this.orderLinesRepository = orderLinesRepository;
        this.repositoryManager = repositoryManager;
    }

    @Test
    @Transactional
    public void testThatOrderLinesCanBeCreatedAndObtained() {
        final List<OrderLine> expectedOrderLines = TestFactoryData.createMockOrderLines(3);

        orderLinesRepository.createOrderLine(expectedOrderLines, repositoryManager);
        List<OrderLine> actualOrderLines = orderLinesRepository.getAllOrderLines();

        assertEquals(expectedOrderLines.size(), actualOrderLines.size());

        for (int i = 0; i < expectedOrderLines.size(); i++) {
            OrderLine expectedOrderLine = expectedOrderLines.get(i);
            OrderLine actualOrderLine = actualOrderLines.get(i);

            assertEquals(expectedOrderLine.getQuantity(), actualOrderLine.getQuantity());
            assertEquals(expectedOrderLine.getSize(), actualOrderLine.getSize());
            assertEquals(expectedOrderLine.getColor(), actualOrderLine.getColor());

            assertEquals(expectedOrderLine.getOrder().getOrderDate(), actualOrderLine.getOrder().getOrderDate());

            assertEquals(expectedOrderLine.getOrder().getOrderStatusType().getStatus(), actualOrderLine.getOrder().getOrderStatusType().getStatus());

            assertEquals(expectedOrderLine.getOrder().getCustomer().getUsername(), actualOrderLine.getOrder().getCustomer().getUsername());
            assertEquals(expectedOrderLine.getOrder().getCustomer().getPassword(), actualOrderLine.getOrder().getCustomer().getPassword());


        }

    }

}
