package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.products.CategoryRepository;
import com.shopallday.storage.domain.usecases.UseCaseNoParam;

import java.util.List;

public class GetCategoryUseCase implements UseCaseNoParam<List<Category>> {

    private final CategoryRepository categoryRepository;
    public GetCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> execute() {
        return categoryRepository.getCategories();
    }
}
