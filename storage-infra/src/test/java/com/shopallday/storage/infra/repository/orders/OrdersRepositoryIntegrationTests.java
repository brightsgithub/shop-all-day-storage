package com.shopallday.storage.infra.repository.orders;

import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.repository.OrdersRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.infra.repository.BaseIntegrationTests;
import com.shopallday.storage.infra.repository.TestFactoryData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdersRepositoryIntegrationTests extends BaseIntegrationTests {

    private OrdersRepository ordersRepository;
    private RepositoryManager repositoryManager;

    @Autowired
    public OrdersRepositoryIntegrationTests(OrdersRepository ordersRepository, RepositoryManager repositoryManager) {
        this.ordersRepository = ordersRepository;
        this.repositoryManager = repositoryManager;
    }

    @Test
    @Transactional
    public void testThatOrdersCanBeCreatedAndObtained() {
        final List<Order> expectedOrders = TestFactoryData.createMockOrders(3);
        ordersRepository.createOrder(expectedOrders, repositoryManager);

        List<Order> actualOrders = ordersRepository.getAllOrders();

        assertEquals(expectedOrders.size(), actualOrders.size());

        for (int i = 0; i < expectedOrders.size(); i++) {
            Order expectedOrder = expectedOrders.get(i);
            Order actualOrder = actualOrders.get(i);

            assertEquals(expectedOrder.getCustomer().getUsername(), actualOrder.getCustomer().getUsername());
            assertEquals(expectedOrder.getCustomer().getPassword(), actualOrder.getCustomer().getPassword());
            assertEquals(expectedOrder.getOrderStatusType().getStatus(), actualOrder.getOrderStatusType().getStatus());
        }
    }
}
