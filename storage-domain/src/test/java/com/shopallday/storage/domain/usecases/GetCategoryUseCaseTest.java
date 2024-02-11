package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCategoryUseCaseTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private GetCategoryUseCase getCategoryUseCase;

    @Test
    public void testExecute() {
        // Arrange
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(new Category(1L, "Category1"));
        expectedCategories.add(new Category(2L, "Category2"));

        // Mocking behavior of the categoryRepository
        when(categoryRepository.getCategories()).thenReturn(expectedCategories);

        // Act
        List<Category> actualCategories = getCategoryUseCase.execute();

        // Assert
        assertEquals(expectedCategories.size(), actualCategories.size());
        for (int i = 0; i < expectedCategories.size(); i++) {
            Category expectedCategory = expectedCategories.get(i);
            Category actualCategory = actualCategories.get(i);
            assertEquals(expectedCategory.getCategoryId(), actualCategory.getCategoryId());
            assertEquals(expectedCategory.getCategoryName(), actualCategory.getCategoryName());
        }
    }
}
