package com.shopallday.storage.domain.repository.products;

import com.shopallday.storage.domain.models.Category;

import java.util.List;

public interface CategoryRepository {
    void createCategories(List<Category> categories);
    Category createCategory(Category category);
    List<Category> getCategories();

    void updateCategories(List<Category> categories);
    Category updateCategory(Category category);

    void deleteCategory(Category category);
    List<Category> findCategoryById(Long id);
    boolean isExists(Long id);

    void deleteCategoryById(Long id);
    void deleteAll();
}
