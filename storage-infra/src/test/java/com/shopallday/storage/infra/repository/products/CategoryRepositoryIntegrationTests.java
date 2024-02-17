package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.CategoryRepository;
import com.shopallday.storage.infra.repository.BaseIntegrationTests;
import com.shopallday.storage.infra.repository.TestFactoryData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryRepositoryIntegrationTests extends BaseIntegrationTests {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryRepositoryIntegrationTests(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Test
    @Transactional
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
