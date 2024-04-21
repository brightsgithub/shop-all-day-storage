package com.shopallday.storage.app.controllers.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopallday.storage.app.BaseControllerIntegrationTests;
import com.shopallday.storage.app.TestFactoryData;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.OrderLineDto;
import com.shopallday.storage.app.services.orders.OrderLinesService;
import com.shopallday.storage.domain.models.OrderLine;
import com.shopallday.storage.domain.usecases.DeleteAllUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderLinesControllerIntegrationTests extends BaseControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final OrderLinesService orderLinesService;
    private final ObjectMapper objectMapper;
    private final Mapper<OrderLine, OrderLineDto> orderLinesMapper;


    @Autowired
    public OrderLinesControllerIntegrationTests(
            @Qualifier("orderLinesMapper") Mapper<OrderLine, OrderLineDto> orderLinesMapper,
            MockMvc mockMvc,
            OrderLinesService orderLinesService,
            ObjectMapper objectMapper,
            DeleteAllUseCase deleteAllUseCase
    ) {
        super(deleteAllUseCase);
        this.mockMvc = mockMvc;
        this.orderLinesService = orderLinesService;
        this.objectMapper = objectMapper;
        this.orderLinesMapper = orderLinesMapper;
    }

    @Test
    public void testGetAllOrderLinesReturns200() throws Exception {
        // Arrange
        final int numberOfOrderLines = 1;
        for (OrderLineDto orderLineDto :
                orderLinesMapper.mapFromDomainToDto(TestFactoryData.createMockOrderLines(numberOfOrderLines))) {
            orderLinesService.createOrderLine(orderLineDto);
        }

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/order-lines")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        OrderLineDto[] retrievedOrderLines = objectMapper.readValue(responseContent, OrderLineDto[].class);
        assertEquals(numberOfOrderLines, retrievedOrderLines.length);
    }

    @Test
    public void testCreateOrderLineReturns201() throws Exception {
        // Arrange
        OrderLineDto orderLineDto =
                orderLinesMapper.mapFromDomainToDto(TestFactoryData.createMockOrderLines(1).get(0));

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/order-lines")
                        .content(objectMapper.writeValueAsString(orderLineDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateOrderLineReturns200() throws Exception {
        // Arrange
        OrderLineDto orderLineDto =
                orderLinesMapper.mapFromDomainToDto(TestFactoryData.createMockOrderLines(1).get(0));
        OrderLineDto createdOrderLineDto = orderLinesService.createOrderLine(orderLineDto);

        // Update some fields
        createdOrderLineDto.setQuantity(10);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/storage/v1/order-lines/{id}", createdOrderLineDto.getOrderLinesId())
                        .content(objectMapper.writeValueAsString(createdOrderLineDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPartiallyUpdateOrderLineReturns200() throws Exception {
        // Arrange
        OrderLineDto orderLineDto =
                orderLinesMapper.mapFromDomainToDto(TestFactoryData.createMockOrderLines(1).get(0));
        OrderLineDto createdOrderLineDto = orderLinesService.createOrderLine(orderLineDto);

        // Update a single field
        String updatedFieldJson = "{\"quantity\": 20}";

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/storage/v1/order-lines/{id}", createdOrderLineDto.getOrderLinesId())
                        .content(updatedFieldJson)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteOrderLineReturns200() throws Exception {
        // Arrange
        OrderLineDto orderLineDto = orderLinesMapper.mapFromDomainToDto(TestFactoryData.createMockOrderLines(1).get(0));
        OrderLineDto createdOrderLineDto = orderLinesService.createOrderLine(orderLineDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/storage/v1/order-lines/{id}", createdOrderLineDto.getOrderLinesId())
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

