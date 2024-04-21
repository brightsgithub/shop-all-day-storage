package com.shopallday.storage.app.controllers.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopallday.storage.app.BaseControllerIntegrationTests;
import com.shopallday.storage.app.TestFactoryData;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CustomerShippingAddressDto;
import com.shopallday.storage.app.services.customer.CustomerShippingAddressService;
import com.shopallday.storage.domain.models.CustomerShippingAddress;
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

public class CustomerShippingAddressControllerIntegrationTests extends BaseControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final CustomerShippingAddressService customerShippingAddressService;
    private final ObjectMapper objectMapper;
    private final Mapper<CustomerShippingAddress, CustomerShippingAddressDto> customerShippingAddressMapper;

    @Autowired
    public CustomerShippingAddressControllerIntegrationTests(
            @Qualifier("customerShipAddressMapper") Mapper<CustomerShippingAddress, CustomerShippingAddressDto> customerShippingAddressMapper,
            MockMvc mockMvc,
            CustomerShippingAddressService customerShippingAddressService,
            ObjectMapper objectMapper,
            DeleteAllUseCase deleteAllUseCase
    ) {
        super(deleteAllUseCase);
        this.mockMvc = mockMvc;
        this.customerShippingAddressService = customerShippingAddressService;
        this.objectMapper = objectMapper;
        this.customerShippingAddressMapper = customerShippingAddressMapper;
    }

    @Test
    public void testGetAllShippingAddressesReturns200() throws Exception {
        // Arrange
        final int numberOfShippingAddresses = 1;
        for (CustomerShippingAddressDto shippingAddressDto :
                customerShippingAddressMapper.mapFromDomainToDto(TestFactoryData.createMockCustomerShippingAddresses(numberOfShippingAddresses))) {
            customerShippingAddressService.create(shippingAddressDto);
        }

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/shipping-address")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        CustomerShippingAddressDto[] retrievedShippingAddresses = objectMapper.readValue(responseContent, CustomerShippingAddressDto[].class);
        assertEquals(numberOfShippingAddresses, retrievedShippingAddresses.length);
    }

    @Test
    public void testCreateShippingAddressReturns201() throws Exception {
        // Arrange
        CustomerShippingAddressDto shippingAddressDto = customerShippingAddressMapper.mapFromDomainToDto(TestFactoryData.createMockCustomerShippingAddresses(1).get(0));

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/shipping-address")
                        .content(objectMapper.writeValueAsString(shippingAddressDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateShippingAddressReturns200() throws Exception {
        // Arrange
        CustomerShippingAddress shippingAddress = TestFactoryData.createMockCustomerShippingAddresses(1).get(0);
        CustomerShippingAddressDto shippingAddressDto = customerShippingAddressMapper.mapFromDomainToDto(shippingAddress);
        CustomerShippingAddressDto createdShippingAddressDto = customerShippingAddressService.create(shippingAddressDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/storage/v1/shipping-address/{id}", createdShippingAddressDto.getShippingAddressId())
                        .content(objectMapper.writeValueAsString(createdShippingAddressDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPartiallyUpdateShippingAddressReturns200() throws Exception {
        // Arrange
        CustomerShippingAddress shippingAddress = TestFactoryData.createMockCustomerShippingAddresses(1).get(0);
        CustomerShippingAddressDto shippingAddressDto = customerShippingAddressMapper.mapFromDomainToDto(shippingAddress);
        CustomerShippingAddressDto createdShippingAddressDto = customerShippingAddressService.create(shippingAddressDto);

        // edited
        String jsonFieldToPatch = """
        {
            "address1": "Updated address1",
            "address2": "Updated address2"
        }
        """;

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/storage/v1/shipping-address/{id}", createdShippingAddressDto.getShippingAddressId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFieldToPatch)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        // confirm the output has actually been changed
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        CustomerShippingAddress retrievedCustomerShippingAddress = objectMapper.readValue(responseContent, CustomerShippingAddress.class);
        assertEquals(retrievedCustomerShippingAddress.getAddress1(), "Updated address1");
        assertEquals(retrievedCustomerShippingAddress.getAddress2(), "Updated address2");
    }

    @Test
    public void testDeleteShippingAddressByIdReturns200() throws Exception {
        // Arrange
        CustomerShippingAddress shippingAddress = TestFactoryData.createMockCustomerShippingAddresses(1).get(0);
        CustomerShippingAddressDto shippingAddressDto = customerShippingAddressMapper.mapFromDomainToDto(shippingAddress);
        CustomerShippingAddressDto createdShippingAddressDto = customerShippingAddressService.create(shippingAddressDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/storage/v1/shipping-address/{id}", createdShippingAddressDto.getShippingAddressId())
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

