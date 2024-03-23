package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.products.CategoryRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import java.util.List;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.CATEGORY_NOT_FOUND;

public class GetCategoryByIdUseCase implements UseCase<Category, Long> {

    private final CategoryRepository categoryRepository;

    public GetCategoryByIdUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(Long param) throws Exception {
        List<Category> categoryList = categoryRepository.findCategoryById(param);
        if (categoryList.isEmpty()) {
            throw new ReadException("Category with id "+param+" could not be found.", CATEGORY_NOT_FOUND);
        }
        return categoryList.get(0);
    }
}
