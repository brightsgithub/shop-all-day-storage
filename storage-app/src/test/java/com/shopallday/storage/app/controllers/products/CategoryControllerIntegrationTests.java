package com.shopallday.storage.app.controllers.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopallday.storage.app.BaseControllerIntegrationTests;
import com.shopallday.storage.app.TestFactoryData;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CategoryDto;
import com.shopallday.storage.app.services.products.CategoryService;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.Category;
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

public class CategoryControllerIntegrationTests extends BaseControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final Mapper<Category, CategoryDto> categoryMapper;
    private final ObjectMapper objectMapper;
    private final CategoryService categoryService;

    @Autowired
    public CategoryControllerIntegrationTests(
            MockMvc mockMvc,
            @Qualifier("categoryMapper") Mapper<Category, CategoryDto> categoryMapper,
            ObjectMapper objectMapper,
            CategoryService categoryService,
            DeleteAllUseCase deleteAllUseCase
    ) {
        super(deleteAllUseCase);
        this.mockMvc = mockMvc;
        this.categoryMapper = categoryMapper;
        this.objectMapper = objectMapper;
        this.categoryService = categoryService;
    }

    @Test
    @Transactional
    public void testGetAllCategoriesReturns200() throws Exception {
        // Arrange
        final int numberOfCategories = 3;
        for (Category category : TestFactoryData.createMockCategories(numberOfCategories)) {
            categoryService.createCategory(categoryMapper.mapFromDomainToDto(category));
        }

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(numberOfCategories));
    }

    @Test
    @Transactional
    public void testGetCategoryByIdReturns200() throws Exception {
        // Arrange
        final Category category = TestFactoryData.createMockCategories(1).get(0);
        final CategoryDto categoryDto = categoryMapper.mapFromDomainToDto(category);
        final CategoryDto createdCategoryDto = categoryService.createCategory(categoryDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/category/" + createdCategoryDto.getCategoryId())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(createdCategoryDto.getCategoryId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryName").value(createdCategoryDto.getCategoryName()));
    }

    @Test
    @Transactional
    public void testGetCategoryByIdNotFoundReturns404() throws Exception {
        // Arrange
        final Long nonExistingCategoryId = 999L;

        // Act & Assert
        assertThrows(ReadException.class, () -> categoryService.getCategoryById(nonExistingCategoryId));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/storage/v1/category/" + nonExistingCategoryId)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Transactional
    public void testCreateCategoryReturns201() throws Exception {
        // Arrange
        final Category category = TestFactoryData.createMockCategories(1).get(0);
        final CategoryDto categoryDto = categoryMapper.mapFromDomainToDto(category);
        final String categoryDtoJson = objectMapper.writeValueAsString(categoryDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/storage/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryDtoJson)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryName").value(categoryDto.getCategoryName()));
    }

    @Test
    @Transactional
    public void testUpdateCategoryReturns200() throws Exception {
        // Arrange
        final Category category = TestFactoryData.createMockCategories(1).get(0);
        final CategoryDto createdCategoryDto = categoryService.createCategory(categoryMapper.mapFromDomainToDto(category));
        createdCategoryDto.setCategoryName("Updated Category");
        final String categoryDtoJson = objectMapper.writeValueAsString(createdCategoryDto);

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/storage/v1/category/" + createdCategoryDto.getCategoryId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryDtoJson)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(createdCategoryDto.getCategoryId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryName").value("Updated Category"));
    }

    @Test
    @Transactional
    public void testPartiallyUpdateCategoryReturns200() throws Exception {
        // Arrange
        final Category category = TestFactoryData.createMockCategories(1).get(0);
        final CategoryDto createdCategoryDto = categoryService.createCategory(categoryMapper.mapFromDomainToDto(category));
        String jsonFieldToPatch = """
            {
                "categoryName": "Patched Category"
            }
            """;

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/storage/v1/category/" + createdCategoryDto.getCategoryId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFieldToPatch)
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(createdCategoryDto.getCategoryId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryName").value("Patched Category"));
    }

    @Test
    @Transactional
    public void testDeleteCategoryReturns200() throws Exception {
        // Arrange
        final Category category = TestFactoryData.createMockCategories(1).get(0);
        final CategoryDto createdCategoryDto = categoryService.createCategory(categoryMapper.mapFromDomainToDto(category));

        // Act
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/storage/v1/category/" + createdCategoryDto.getCategoryId())
        );

        // Assert
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
