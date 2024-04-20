package com.shopallday.storage.app.controllers.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopallday.storage.app.BaseControllerIntegrationTests;
import com.shopallday.storage.app.TestFactoryData;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CustomerDto;
import com.shopallday.storage.app.services.customer.CustomerService;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.models.Customer;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerControllerIntegrationTests extends BaseControllerIntegrationTests {

    private MockMvc mockMvc;
    private final Mapper<Customer, CustomerDto> customerMapper;
    private final ObjectMapper objectMapper;
    private final CustomerService customerService;

    @Autowired
    public CustomerControllerIntegrationTests(
            MockMvc mockMvc,
            @Qualifier("customerMapper") Mapper<Customer, CustomerDto> customerMapper,
            ObjectMapper objectMapper,
            CustomerService customerService,
            DeleteAllUseCase deleteAllUseCase) {
        super(deleteAllUseCase);
        this.mockMvc = mockMvc;
        this.customerMapper = customerMapper;
        this.objectMapper = objectMapper;
        this.customerService = customerService;
    }

    @Test
    @Transactional
    public void testThatCustomerCreatedAndReturns201() throws Exception {
        final Customer customer = TestFactoryData.createMockCustomers(1).get(0);
        final CustomerDto customerDto = customerMapper.mapFromDomainToDto(customer);
        final String customerDtoJson = objectMapper.writeValueAsString(customerDto);
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

        checkCustomerHasPopulatedCorrectly(resultActions, customerDto);
    }

    @Test
    @Transactional
    public void testThatDeleteDeletesCustomerAndReturns200() throws Exception {
        final Customer customer = TestFactoryData.createMockCustomers(1).get(0);
        final CustomerDto createdCustomer = customerService.createCustomer(customer);

        // ensure we can actually get the created customer
        assertEquals(customerService.getCustomerById(createdCustomer.getCustomerId()), createdCustomer);

        // execute a Delete request to delete the customer
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/storage/v1/customers/"+createdCustomer.getCustomerId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

        // Make sure we get an error when trying to look up a deleted customer
        assertThrows(ReadCustomerException.class, () -> customerService.getCustomerById(createdCustomer.getCustomerId()));
    }

    @Test
    @Transactional
    public void testThatCustomerCreatedTwiceReturnsReturnsConflict409() throws Exception {
        final Customer customer = TestFactoryData.createMockCustomers(1).get(0);
        final CustomerDto customerDto = customerMapper.mapFromDomainToDto(customer);
        final String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
        checkCustomerHasPopulatedCorrectly(resultActions, customerDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isConflict()
        );
    }

    @Test
    @Transactional
    public void testThatCallingPatchShouldUpdateCustomerAndReturnsReturn200() throws Exception {
        final Customer customer = TestFactoryData.createMockCustomers(1).get(0);
        final CustomerDto  createdCustomer = customerService.createCustomer(customer);

        String jsonFieldToPatch = """
        {
            "firstName": "edited"
        }
        """;

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/storage/v1/customers/"+createdCustomer.getCustomerId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFieldToPatch)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName").value("edited")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.customerId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.lastName").value(createdCustomer.getLastName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value(createdCustomer.getEmail())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.address1").value(createdCustomer.getAddress1())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.username").value(createdCustomer.getUsername())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.postCode").value(createdCustomer.getPostCode())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.phoneNumber").value(createdCustomer.getPhoneNumber())
        );
    }

    @Test
    @Transactional
    public void testThatCallingPutShouldUpdateCustomerAndReturnReturn200() throws Exception {
        final Customer customer = TestFactoryData.createMockCustomers(1).get(0);
        final CustomerDto  createdCustomer = customerService.createCustomer(customer);

        createdCustomer.setFirstName("edited");
        final String customerDtoJson = objectMapper.writeValueAsString(createdCustomer);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/storage/v1/customers/"+createdCustomer.getCustomerId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName").value("edited")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.customerId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.lastName").value(createdCustomer.getLastName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value(createdCustomer.getEmail())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.address1").value(createdCustomer.getAddress1())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.username").value(createdCustomer.getUsername())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.postCode").value(createdCustomer.getPostCode())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.phoneNumber").value(createdCustomer.getPhoneNumber())
        );
    }

    @Test
    @Transactional
    public void testThatGetByIdReturns200() throws Exception {
        final List<Customer> customers = TestFactoryData.createMockCustomers(2);
        final CustomerDto createdCustomerDto = customerService.createCustomer(customers.get(0));
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/customers/"+createdCustomerDto.getCustomerId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
        checkCustomerHasPopulatedCorrectly(resultActions, createdCustomerDto);
    }

    @Test
    @Transactional
    public void testThatGetAllCustomersReturns200List() throws Exception {
        final List<Customer> customers = TestFactoryData.createMockCustomers(2);
        final CustomerDto customer1 = customerService.createCustomer(customers.get(0));
        final CustomerDto customer2 = customerService.createCustomer(customers.get(1));

        // Confirm we get an OK and that the data returned matches what we supplied
        mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].customerId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].firstName").value(customer1.getFirstName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].lastName").value(customer1.getLastName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].customerId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].firstName").value(customer2.getFirstName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].lastName").value(customer2.getLastName())
        );
    }

    private void checkCustomerHasPopulatedCorrectly(ResultActions resultActions, CustomerDto customerDto) throws Exception {
        resultActions
        .andExpect(
                MockMvcResultMatchers.jsonPath("$.customerId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.lastName").value(customerDto.getLastName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName").value(customerDto.getFirstName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value(customerDto.getEmail())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.address1").value(customerDto.getAddress1())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.username").value(customerDto.getUsername())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.postCode").value(customerDto.getPostCode())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.phoneNumber").value(customerDto.getPhoneNumber())
        );
    }
}
