package com.shopallday.storage.app.controllers.products;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopallday.storage.app.BaseControllerIntegrationTests;
import com.shopallday.storage.app.TestFactoryData;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.ProductStockDto;
import com.shopallday.storage.app.services.products.ProductStockService;
import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.usecases.DeleteAllUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductStockControllerIntegrationTests extends BaseControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final ProductStockService productStockService;
    private final Mapper<ProductStock, ProductStockDto> mapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductStockControllerIntegrationTests(
            MockMvc mockMvc,
            @Qualifier("productStockMapper") Mapper<ProductStock, ProductStockDto> mapper,
            ProductStockService productStockService,
            DeleteAllUseCase deleteAllUseCase,
            ObjectMapper objectMapper) {
        super(deleteAllUseCase);
        this.mockMvc = mockMvc;
        this.productStockService = productStockService;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testGetAllProductStocksReturns200() throws Exception {
        // Arrange
        final int numberOfProductStocks = 3;
        List<ProductStockDto> productStockDtos = mapper.mapFromDomainToDto(TestFactoryData.createMockProductStock(numberOfProductStocks));
        for (ProductStockDto productStockDto : productStockDtos) {
            productStockService.createProductStock(productStockDto);
        }

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/product-stock")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        List<ProductStockDto> retrievedProductStockDtos = objectMapper.readValue(responseContent, new TypeReference<>() {});
        assertEquals(numberOfProductStocks, retrievedProductStockDtos.size());
    }

    @Test
    public void testGetProductStockByIdReturns200() throws Exception {
        // Arrange
        ProductStock productStock = TestFactoryData.createMockProductStock(1).get(0);
        ProductStockDto createdProductStockDto = productStockService.createProductStock(mapper.mapFromDomainToDto(productStock));

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/product-stock/{id}", createdProductStockDto.getProductStockId())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        ProductStockDto retrievedProductStockDto = objectMapper.readValue(responseContent, ProductStockDto.class);
        assertEquals(createdProductStockDto.getProductStockId(), retrievedProductStockDto.getProductStockId());
    }

    @Test
    public void testGetNonExistingProductStockByIdReturns404() throws Exception {
        // Arrange
        final Long nonExistingProductStockId = 999L;

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/product-stock/{id}", nonExistingProductStockId)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateProductStockReturns201() throws Exception {
        // Arrange
        ProductStockDto productStockDto = mapper.mapFromDomainToDto(TestFactoryData.createMockProductStock(1).get(0));
        String requestJson = objectMapper.writeValueAsString(productStockDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/product-stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        ProductStockDto createdProductStockDto = objectMapper.readValue(responseContent, ProductStockDto.class);
        assertEquals(productStockDto.getPrice(), createdProductStockDto.getPrice());
    }

    @Test
    public void testUpdateProductStockReturns200() throws Exception {
        // Arrange
        ProductStock productStock = TestFactoryData.createMockProductStock(1).get(0);
        ProductStockDto productStockDto = mapper.mapFromDomainToDto(productStock);
        ProductStockDto createdProductStockDto = productStockService.createProductStock(productStockDto);
        productStockDto.setQuantity(10); // Update quantity
        String requestJson = objectMapper.writeValueAsString(createdProductStockDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/storage/v1/product-stock/{id}", createdProductStockDto.getProductStockId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        ProductStockDto updatedProductStockDto = objectMapper.readValue(responseContent, ProductStockDto.class);
        assertEquals(productStockDto.getQuantity(), updatedProductStockDto.getQuantity());
    }

    @Test
    public void testPartiallyUpdateProductStockReturns200() throws Exception {
        // Arrange
        ProductStock productStock = TestFactoryData.createMockProductStock(1).get(0);
        ProductStockDto createdProductStockDto = productStockService.createProductStock(mapper.mapFromDomainToDto(productStock));

        int updatedQuantity = 10;
        String jsonFieldToPatch = """
        {
            "quantity": 10
        }
        """;

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/storage/v1/product-stock/{id}", createdProductStockDto.getProductStockId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFieldToPatch)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        ProductStockDto updatedProductStockDto = objectMapper.readValue(responseContent, ProductStockDto.class);
        assertEquals(updatedQuantity, updatedProductStockDto.getQuantity());
    }

    @Test
    public void testDeleteProductStockReturns200() throws Exception {
        // Arrange
        ProductStock productStock = TestFactoryData.createMockProductStock(1).get(0);
        ProductStockDto createdProductStockDto = productStockService.createProductStock(mapper.mapFromDomainToDto(productStock));

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/storage/v1/product-stock/{id}", createdProductStockDto.getProductStockId())
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
