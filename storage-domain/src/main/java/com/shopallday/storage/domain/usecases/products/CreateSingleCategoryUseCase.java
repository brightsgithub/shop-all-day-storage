package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.products.CategoryRepository;
import com.shopallday.storage.domain.usecases.UseCase;

public class CreateSingleCategoryUseCase implements UseCase<Category, Category> {

    private final CategoryRepository categoryRepository;

    public CreateSingleCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(Category param) throws CreateException {
        try {
            return categoryRepository.createCategory(param);
        } catch (Exception e) {
            throw new CreateException("Could not create ctegory", BusinessErrorCodes.CATEGORY_COULD_NOT_BE_CREATED);
        }
    }
}
