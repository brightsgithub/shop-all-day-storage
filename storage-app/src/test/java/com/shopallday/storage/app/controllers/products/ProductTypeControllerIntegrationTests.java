package com.shopallday.storage.app.controllers.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopallday.storage.app.BaseControllerIntegrationTests;
import com.shopallday.storage.app.TestFactoryData;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.ProductTypeDto;
import com.shopallday.storage.app.services.products.ProductTypeService;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.usecases.DeleteAllUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTypeControllerIntegrationTests extends BaseControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final Mapper<ProductType, ProductTypeDto> productTypeMapper;
    private final ObjectMapper objectMapper;
    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeControllerIntegrationTests(
            MockMvc mockMvc,
            @Qualifier("productTypeMapper") Mapper<ProductType, ProductTypeDto> productTypeMapper,
            ObjectMapper objectMapper,
            ProductTypeService productTypeService,
            DeleteAllUseCase deleteAllUseCase
    ) {
        super(deleteAllUseCase);
        this.mockMvc = mockMvc;
        this.productTypeMapper = productTypeMapper;
        this.objectMapper = objectMapper;
        this.productTypeService = productTypeService;
    }

    @Test
    public void testGetAllProductTypesReturns200() throws Exception {
        // Arrange
        final int numberOfProductTypes = 3;
        for (ProductType productType : TestFactoryData.createMockProductTypes(numberOfProductTypes)) {
            productTypeService.createProductType(productTypeMapper.mapFromDomainToDto(productType));
        }

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/product-type")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(numberOfProductTypes));
    }

    @Test
    public void testGetProductTypeByIdReturns200() throws Exception {
        // Arrange
        final ProductType productType = TestFactoryData.createMockProductTypes(1).get(0);
        final ProductTypeDto productTypeDto = productTypeMapper.mapFromDomainToDto(productType);
        final ProductTypeDto createdProductTypeDto = productTypeService.createProductType(productTypeDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/product-type/" + createdProductTypeDto.getProductTypeId())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productTypeId").value(createdProductTypeDto.getProductTypeId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productTypeName").value(createdProductTypeDto.getProductTypeName()));
    }

    @Test
    public void testGetProductTypeByIdNotFoundReturns404() throws Exception {
        // Arrange
        final Long nonExistingProductTypeId = 999L;

        // Act & Assert
        assertThrows(ReadException.class, () -> productTypeService.getProductTypeById(nonExistingProductTypeId));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/product-type/" + nonExistingProductTypeId)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateProductTypeReturns201() throws Exception {
        // Arrange
        ProductTypeDto productTypeDto = productTypeMapper.mapFromDomainToDto(TestFactoryData.createMockProductTypes(1)).get(0);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/product-type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productTypeDto))
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateProductTypeReturns200() throws Exception {
        // Arrange
        ProductType productType = TestFactoryData.createMockProductTypes(1).get(0);
        ProductTypeDto productTypeDto = productTypeMapper.mapFromDomainToDto(productType);
        ProductTypeDto createdProductTypeDto = productTypeService.createProductType(productTypeDto);

        // Modify product type name
        createdProductTypeDto.setProductTypeName("Updated Product Type Name");

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/storage/v1/product-type/{id}", createdProductTypeDto.getProductTypeId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdProductTypeDto))
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        // Verify if the product type was updated
        ProductTypeDto updatedProductTypeDto = productTypeService.getProductTypeById(createdProductTypeDto.getProductTypeId());
        assertEquals(createdProductTypeDto.getProductTypeName(), updatedProductTypeDto.getProductTypeName());
    }

    @Test
    public void testPartiallyUpdateProductTypeReturns200() throws Exception {
        // Arrange
        ProductType productType = TestFactoryData.createMockProductTypes(1).get(0);
        ProductTypeDto productTypeDto = productTypeMapper.mapFromDomainToDto(productType);
        ProductTypeDto createdProductTypeDto = productTypeService.createProductType(productTypeDto);

        // Partial update request body
        String jsonFieldToPatch = """
        {
            "productTypeName": "Updated Product Type Name"
        }
        """;

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/storage/v1/product-type/{id}", createdProductTypeDto.getProductTypeId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFieldToPatch)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        // Verify if the product type was updated
        ProductTypeDto updatedProductTypeDto = productTypeService.getProductTypeById(createdProductTypeDto.getProductTypeId());
        assertEquals("Updated Product Type Name", updatedProductTypeDto.getProductTypeName());
    }

    @Test
    public void testDeleteProductTypeReturns200() throws Exception {
        // Arrange
        ProductTypeDto productTypeDto = productTypeMapper.mapFromDomainToDto(TestFactoryData.createMockProductTypes(1)).get(0);
        ProductTypeDto createdProductTypeDto = productTypeService.createProductType(productTypeDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/storage/v1/product-type/{id}", createdProductTypeDto.getProductTypeId())
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        // Verify if the product type was deleted
        assertThrows(ReadException.class, () -> productTypeService.getProductTypeById(createdProductTypeDto.getProductTypeId()));
    }

    @Test
    public void testGetProductTypesByCategoryIdReturns200() throws Exception {
        // Arrange
        final Long categoryId = 1L;
        List<ProductType> mockProductTypes = TestFactoryData.createMockProductTypes(3);
        List<ProductType> createdProductTypes = new ArrayList<>();

        for(ProductType productType: mockProductTypes) {
            final ProductTypeDto dto = productTypeMapper.mapFromDomainToDto(productType);
            final ProductTypeDto createdDto = productTypeService.createProductType(dto);
            final ProductType createdDomain = productTypeMapper.mapFromDtoToDomain(createdDto);
            createdProductTypes.add(createdDomain);
        }

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/product-type/category/{id}", createdProductTypes.get(0).getCategory().getCategoryId())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    public void testGetProductTypesByCategoryIdNotFoundReturns200EmptyList() throws Exception {
        // Arrange
        final Long nonExistingCategoryId = 999L;
        List<ProductType> mockProductTypes = TestFactoryData.createMockProductTypes(1);
        final ProductTypeDto createdDto = productTypeService.createProductType(productTypeMapper.mapFromDomainToDto(mockProductTypes.get(0)));
        // Act & Assert


        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/product-type/category/{id}" , nonExistingCategoryId)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }
}
