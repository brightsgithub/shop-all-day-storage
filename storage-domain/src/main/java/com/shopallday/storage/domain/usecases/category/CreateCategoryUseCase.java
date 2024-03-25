package com.shopallday.storage.domain.usecases.category;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.products.CategoryRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoReturnVal;

import java.util.List;

public class CreateCategoryUseCase implements UseCaseNoReturnVal<List<Category>> {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void execute(List<Category> categoryList) {
        categoryRepository.createCategories(categoryList);
    }
}
