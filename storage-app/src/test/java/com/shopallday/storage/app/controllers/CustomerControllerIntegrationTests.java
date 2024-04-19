package com.shopallday.storage.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopallday.storage.app.BaseControllerIntegrationTests;
import com.shopallday.storage.app.TestFactoryData;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.domain.models.Customer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class CustomerControllerIntegrationTests extends BaseControllerIntegrationTests {

    private MockMvc mockMvc;
    private final Mapper<Customer, CustomerDto> customerMapper;
    private final ObjectMapper objectMapper;
    @Autowired
    public CustomerControllerIntegrationTests(
            MockMvc mockMvc,
            @Qualifier("customerMapper") Mapper<Customer, CustomerDto> customerMapper, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.customerMapper = customerMapper;
        this.objectMapper = objectMapper;
    }

    @Test
    @Transactional
    public void testThatCustomerCreatedAndReturns201() throws Exception {
        final Customer customer = TestFactoryData.createMockCustomers(1).get(0);
        final CustomerDto customerDto = customerMapper.mapFromDomainToDto(customer);
        final String customerDtoJson = objectMapper.writeValueAsString(customerDto);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }
}
