package com.shopallday.storage.app.controllers.products;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopallday.storage.app.BaseControllerIntegrationTests;
import com.shopallday.storage.app.TestFactoryData;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.ProductDto;
import com.shopallday.storage.app.services.products.ProductsService;
import com.shopallday.storage.domain.exceptions.product.ReadProductException;
import com.shopallday.storage.domain.models.Product;
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

import static org.junit.jupiter.api.Assertions.*;

public class ProductsControllerIntegrationTests extends BaseControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final Mapper<Product, ProductDto> productMapper;
    private final ObjectMapper objectMapper;
    private final ProductsService productsService;

    @Autowired
    public ProductsControllerIntegrationTests(
            MockMvc mockMvc,
            @Qualifier("productMapper") Mapper<Product, ProductDto> productMapper,
            ObjectMapper objectMapper,
            ProductsService productsService,
            DeleteAllUseCase deleteAllUseCase
    ) {
        super(deleteAllUseCase);
        this.mockMvc = mockMvc;
        this.productMapper = productMapper;
        this.objectMapper = objectMapper;
        this.productsService = productsService;
    }

    @Test
    public void testCreateProductReturns201() throws Exception {
        // Arrange
        Product product = TestFactoryData.createMockProducts(1).get(0);
        ProductDto productDto = productMapper.mapFromDomainToDto(product);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto))
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateProductReturns200() throws Exception {
        // Arrange
        Product product = TestFactoryData.createMockProducts(1).get(0);
        ProductDto createdProductDto = productsService.createProduct(product);

        // Modify product name
        createdProductDto.setShortTitle("Updated Product Name");

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/storage/v1/products/{id}", createdProductDto.getProductId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdProductDto))
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        // Verify if the product was updated
        ProductDto updatedProductDto = productsService.getProductById(createdProductDto.getProductId());
        assertEquals(createdProductDto.getShortTitle(), updatedProductDto.getShortTitle());
    }

    @Test
    public void testPartiallyUpdateProductReturns200() throws Exception {
        // Arrange
        Product product = TestFactoryData.createMockProducts(1).get(0);
        ProductDto createdProductDto = productsService.createProduct(product);

        // Partial update request body
        String jsonFieldToPatch = """
        {
            "shortTitle": "Updated shortTitle",
            "shortDescription": "Updated shortDescription"          
        }
        """;

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/storage/v1/products/{id}", createdProductDto.getProductId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFieldToPatch)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        // Verify if the product was updated
        ProductDto updatedProductDto = productsService.getProductById(createdProductDto.getProductId());
        assertEquals("Updated shortTitle", updatedProductDto.getShortTitle());
        assertEquals("Updated shortDescription", updatedProductDto.getShortDescription());
    }

    @Test
    public void testDeleteProductReturns200() throws Exception {
        // Arrange
        Product product = TestFactoryData.createMockProducts(1).get(0);
        ProductDto createdProductDto = productsService.createProduct(product);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/storage/v1/products/{id}", createdProductDto.getProductId())
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        // Verify if the product was deleted
        assertThrows(ReadProductException.class, () -> productsService.getProductById(createdProductDto.getProductId()));
    }

    @Test
    public void testGetAllProductsReturns200() throws Exception {
        // Arrange
        final int numberOfProducts = 3;
        List<Product> products = TestFactoryData.createMockProducts(numberOfProducts);
        for (Product product : products) {
            productsService.createProduct(product);
        }

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        List<ProductDto> productDtos = objectMapper.readValue(responseContent, new TypeReference<>() {});
        assertEquals(numberOfProducts, productDtos.size());
    }

    @Test
    public void testGetProductByIdReturns200() throws Exception {
        // Arrange
        Product product = TestFactoryData.createMockProducts(1).get(0);
        ProductDto createdProductDto = productsService.createProduct(product);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/products/{id}", createdProductDto.getProductId())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        ProductDto retrievedProductDto = objectMapper.readValue(responseContent, ProductDto.class);
        assertEquals(createdProductDto.getShortTitle(), retrievedProductDto.getShortTitle());
    }

    @Test
    public void testGetNonExistingProductByIdReturns404() throws Exception {
        // Arrange
        final Long nonExistingProductId = 999L;

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/products/{id}", nonExistingProductId)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
