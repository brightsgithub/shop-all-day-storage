package com.shopallday.storage.app.controllers.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopallday.storage.app.BaseControllerIntegrationTests;
import com.shopallday.storage.app.TestFactoryData;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.app.models.OrderDto;
import com.shopallday.storage.app.services.orders.OrdersService;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.models.Order;
import com.shopallday.storage.domain.usecases.DeleteAllUseCase;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdersControllerIntegrationTests extends BaseControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final OrdersService ordersService;
    private final Mapper<Order, OrderDto> orderMapper;
    private final Mapper<Customer, CustomerDto> customerMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrdersControllerIntegrationTests(
            MockMvc mockMvc,
            @Qualifier("orderMapper") Mapper<Order, OrderDto> orderMapper,
            @Qualifier("customerMapper") Mapper<Customer, CustomerDto> customerMapper,
            OrdersService ordersService,
            DeleteAllUseCase deleteAllUseCase,
            ObjectMapper objectMapper) {
        super(deleteAllUseCase);
        this.mockMvc = mockMvc;
        this.ordersService = ordersService;
        this.orderMapper = orderMapper;
        this.objectMapper = objectMapper;
        this.customerMapper = customerMapper;
    }

    @Test
    @Transactional
    public void testGetAllOrdersReturns200() throws Exception {
        // Arrange
        final int numberOfOrders = 1;
        for (Order order : TestFactoryData.createMockOrders(numberOfOrders)) {
            ordersService.createOrder(orderMapper.mapFromDomainToDto(order));
        }

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        OrderDto[] retrievedOrders = objectMapper.readValue(responseContent, OrderDto[].class);
        assertEquals(numberOfOrders, retrievedOrders.length);
    }

    @Test
    @Transactional
    public void testCreateOrderReturns201() throws Exception {
        // Arrange
        OrderDto orderDto = orderMapper.mapFromDomainToDto(TestFactoryData.createMockOrders(1)).get(0);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/orders")
                        .content(objectMapper.writeValueAsString(orderDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @Transactional
    public void testUpdateOrderReturns200() throws Exception {
        // Arrange
        CustomerDto customerDto = customerMapper.mapFromDomainToDto(TestFactoryData.createMockCustomers(1).get(0));
        Order order = TestFactoryData.createMockOrders(1).get(0);
        OrderDto orderDto = orderMapper.mapFromDomainToDto(order);
        orderDto.setCustomerDto(customerDto);
        OrderDto createdOrderDto = ordersService.createOrder(orderDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/storage/v1/orders/{id}", createdOrderDto.getOrderId())
                        .content(objectMapper.writeValueAsString(createdOrderDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    public void testPartiallyUpdateOrderReturns200() throws Exception {
        // Arrange
        Order order = TestFactoryData.createMockOrders(1).get(0);
        OrderDto orderDto = orderMapper.mapFromDomainToDto(order);
        OrderDto createdOrderDto = ordersService.createOrder(orderDto);

        // edited
        String jsonFieldToPatch = String.format("""
        {
            "orderDate": "%s"
        }
        """, "2024-04-21T08:19:24.584+00:00");

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/storage/v1/orders/{id}", createdOrderDto.getOrderId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFieldToPatch)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    public void testDeleteOrderByIdReturns200() throws Exception {
        // Arrange
        Order order = TestFactoryData.createMockOrders(1).get(0);
        OrderDto orderDto = orderMapper.mapFromDomainToDto(order);
        OrderDto createdOrderDto = ordersService.createOrder(orderDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/storage/v1/orders/{id}", createdOrderDto.getOrderId())
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

