package com.shopallday.storage.app.controllers.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopallday.storage.app.BaseControllerIntegrationTests;
import com.shopallday.storage.app.TestFactoryData;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.BrandDto;
import com.shopallday.storage.app.services.products.BrandsService;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.Brand;
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

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BrandsControllerIntegrationTests extends BaseControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final Mapper<Brand, BrandDto> brandMapper;
    private final ObjectMapper objectMapper;
    private final BrandsService brandsService;

    @Autowired
    public BrandsControllerIntegrationTests(
            MockMvc mockMvc,
            @Qualifier("brandMapper") Mapper<Brand, BrandDto> brandMapper,
            ObjectMapper objectMapper,
            BrandsService brandsService,
            DeleteAllUseCase deleteAllUseCase
    ) {
        super(deleteAllUseCase);
        this.mockMvc = mockMvc;
        this.brandMapper = brandMapper;
        this.objectMapper = objectMapper;
        this.brandsService = brandsService;
    }

    @Test
    @Transactional
    public void testGetAllBrandsReturns200() throws Exception {
        // Arrange
        final int numberOfBrands = 3;
        for (Brand brand : TestFactoryData.createMockBrands(numberOfBrands)) {
            brandsService.createBrand(brandMapper.mapFromDomainToDto(brand));
        }

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/brands")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(numberOfBrands));
    }

    @Test
    @Transactional
    public void testGetBrandByIdReturns200() throws Exception {
        // Arrange
        final Brand brand = TestFactoryData.createMockBrands(1).get(0);
        final BrandDto brandDto = brandMapper.mapFromDomainToDto(brand);
        final BrandDto createdBrandDto = brandsService.createBrand(brandDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/brands/" + createdBrandDto.getBrandId())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(createdBrandDto.getBrandId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandName").value(createdBrandDto.getBrandName()));
    }

    @Test
    @Transactional
    public void testGetBrandByIdNotFoundReturns404() throws Exception {
        // Arrange
        final Long nonExistingBrandId = 999L;

        // Act & Assert
        assertThrows(ReadException.class, () -> brandsService.getBrandById(nonExistingBrandId));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/brands/" + nonExistingBrandId)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Transactional
    public void testCreateBrandReturns201() throws Exception {
        // Arrange
        final BrandDto brandDto = brandMapper.mapFromDomainToDto(TestFactoryData.createMockBrands(1).get(0));

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandDto))
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandName").value(brandDto.getBrandName()));
    }

    @Test
    @Transactional
    public void testUpdateBrandReturns200() throws Exception {
        // Arrange
        final Brand brand = TestFactoryData.createMockBrands(1).get(0);
        final BrandDto brandDto = brandMapper.mapFromDomainToDto(brand);
        final BrandDto createdBrandDto = brandsService.createBrand(brandDto);
        final String updatedBrandName = "Updated Brand";

        // Act
        brandDto.setBrandName(updatedBrandName);
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/storage/v1/brands/" + createdBrandDto.getBrandId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandDto))
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandName").value(updatedBrandName));
    }

    @Test
    @Transactional
    public void testPartiallyUpdateBrandReturns200() throws Exception {
        // Arrange
        final Brand brand = TestFactoryData.createMockBrands(1).get(0);
        final BrandDto brandDto = brandMapper.mapFromDomainToDto(brand);
        final BrandDto createdBrandDto = brandsService.createBrand(brandDto);
        final String updatedBrandName = "Updated Brand";

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/storage/v1/brands/" + createdBrandDto.getBrandId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"brandName\":\"" + updatedBrandName + "\"}")
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandName").value(updatedBrandName));
    }

    @Test
    @Transactional
    public void testDeleteBrandByIdReturns200() throws Exception {
        // Arrange
        final Brand brand = TestFactoryData.createMockBrands(1).get(0);
        final BrandDto brandDto = brandMapper.mapFromDomainToDto(brand);
        final BrandDto createdBrandDto = brandsService.createBrand(brandDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/storage/v1/brands/" + createdBrandDto.getBrandId())
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
