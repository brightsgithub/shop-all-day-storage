package com.shopallday.storage.app.controllers.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopallday.storage.app.BaseControllerIntegrationTests;
import com.shopallday.storage.app.TestFactoryData;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.OrderStatusTypeDto;
import com.shopallday.storage.app.services.orders.OrderStatusTypeService;
import com.shopallday.storage.domain.models.OrderStatusType;
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

public class OrderStatusTypeControllerIntegrationTests extends BaseControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final OrderStatusTypeService orderStatusTypeService;
    private final ObjectMapper objectMapper;
    private final Mapper<OrderStatusType, OrderStatusTypeDto> orderStatusTypeMapper;

    @Autowired
    public OrderStatusTypeControllerIntegrationTests(
            @Qualifier("orderStatusTypeMapper") Mapper<OrderStatusType, OrderStatusTypeDto> orderStatusTypeMapper,
            MockMvc mockMvc,
            OrderStatusTypeService orderStatusTypeService,
            ObjectMapper objectMapper,
            DeleteAllUseCase deleteAllUseCase
    ) {
        super(deleteAllUseCase);
        this.mockMvc = mockMvc;
        this.orderStatusTypeService = orderStatusTypeService;
        this.objectMapper = objectMapper;
        this.orderStatusTypeMapper = orderStatusTypeMapper;
    }

    @Test
    public void testGetAllOrderStatusTypesReturns200() throws Exception {
        // Arrange
        final int numberOfOrderStatusTypes = 1;
        for (OrderStatusTypeDto orderStatusTypeDto :
                orderStatusTypeMapper.mapFromDomainToDto(TestFactoryData.createMockOrderStatusType(numberOfOrderStatusTypes))) {
            orderStatusTypeService.createOrderStatusType(orderStatusTypeDto);
        }

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/order-status-type")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        OrderStatusTypeDto[] retrievedOrderStatusTypes = objectMapper.readValue(responseContent, OrderStatusTypeDto[].class);
        assertEquals(numberOfOrderStatusTypes, retrievedOrderStatusTypes.length);
    }

    @Test
    public void testCreateOrderStatusTypeReturns201() throws Exception {
        // Arrange
        OrderStatusTypeDto orderStatusTypeDto = orderStatusTypeMapper.mapFromDomainToDto(TestFactoryData.createMockOrderStatusType(1).get(0));

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/order-status-type")
                        .content(objectMapper.writeValueAsString(orderStatusTypeDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateOrderStatusTypeReturns200() throws Exception {
        // Arrange
        OrderStatusType orderStatusType = TestFactoryData.createMockOrderStatusType(1).get(0);
        OrderStatusTypeDto orderStatusTypeDto = orderStatusTypeMapper.mapFromDomainToDto(orderStatusType);
        OrderStatusTypeDto createdOrderStatusTypeDto = orderStatusTypeService.createOrderStatusType(orderStatusTypeDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/storage/v1/order-status-type/{id}", createdOrderStatusTypeDto.getOrderStatusTypeId())
                        .content(objectMapper.writeValueAsString(createdOrderStatusTypeDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPartiallyUpdateOrderStatusTypeReturns200() throws Exception {
        // Arrange
        OrderStatusType orderStatusType = TestFactoryData.createMockOrderStatusType(1).get(0);
        OrderStatusTypeDto orderStatusTypeDto = orderStatusTypeMapper.mapFromDomainToDto(orderStatusType);
        OrderStatusTypeDto createdOrderStatusTypeDto = orderStatusTypeService.createOrderStatusType(orderStatusTypeDto);

        // edited
        String jsonFieldToPatch = """
    {
        "status": "Updated status"
    }
    """;

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/storage/v1/order-status-type/{id}", createdOrderStatusTypeDto.getOrderStatusTypeId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFieldToPatch)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteOrderStatusTypeByIdReturns200() throws Exception {
        // Arrange
        OrderStatusType orderStatusType = TestFactoryData.createMockOrderStatusType(1).get(0);
        OrderStatusTypeDto orderStatusTypeDto = orderStatusTypeMapper.mapFromDomainToDto(orderStatusType);
        OrderStatusTypeDto createdOrderStatusTypeDto = orderStatusTypeService.createOrderStatusType(orderStatusTypeDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/storage/v1/order-status-type/{id}", createdOrderStatusTypeDto.getOrderStatusTypeId())
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

}

