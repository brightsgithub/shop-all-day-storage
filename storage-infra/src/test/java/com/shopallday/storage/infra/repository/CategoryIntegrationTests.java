package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryIntegrationTests extends BaseIntegrationTests {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryIntegrationTests(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Test
    public void testThatCategoriesCanBeCreatedAndObtained() {
        List<Category> expectedCategories = TestFactoryData.createMockCategories(3);

        categoryRepository.createCategories(expectedCategories);

        List<Category> actualCategories = categoryRepository.getCategories();

        for (int i = 0; i < expectedCategories.size(); i++) {
            Category expectedCategory = expectedCategories.get(i);
            Category actualCategory = actualCategories.get(i);
            assertEquals(expectedCategory.getCategoryName(), actualCategory.getCategoryName());
        }
    }
}
