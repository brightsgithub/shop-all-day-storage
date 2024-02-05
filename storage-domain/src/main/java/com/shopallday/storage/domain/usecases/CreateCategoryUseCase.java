package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.CategoryRepository;

import java.util.List;

public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void execute(List<Category> categoryList) {
        categoryRepository.createCategories(categoryList);
    }
}
