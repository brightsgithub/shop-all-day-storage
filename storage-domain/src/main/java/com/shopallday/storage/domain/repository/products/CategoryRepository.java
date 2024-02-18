package com.shopallday.storage.domain.repository.products;

import com.shopallday.storage.domain.models.Category;

import java.util.List;

public interface CategoryRepository {
    void createCategories(List<Category> categories);
    void createCategory(Category category);
    List<Category> getCategories();

    void updateCategories(List<Category> categories);
    void updateCategory(Category category);

    void deleteCategory(Category category);
}
