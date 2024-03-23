package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.products.CategoryRepository;
import com.shopallday.storage.domain.usecases.UseCase;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.CATEGORY_COULD_NOT_BE_UPDATED;
import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.CATEGORY_NOT_FOUND;

public class UpdateCategoryUseCase implements UseCase<Category, Category> {

    private final CategoryRepository categoryRepository;

    public UpdateCategoryUseCase(
            CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(Category category) throws ReadException, UpdateException {
        try {
            final Long categoryId = category.getCategoryId();
            if (categoryId == null || !categoryRepository.isExists(categoryId)) {
                throw new ReadException("Cannot find Category with id "+categoryId, CATEGORY_NOT_FOUND);
            }

            return categoryRepository.updateCategory(category);
        } catch (ReadException e) {
            throw e;
        }
        catch (Exception e) {
            throw new UpdateException("Category with id "+category.getCategoryId()+" could not be updated", CATEGORY_COULD_NOT_BE_UPDATED);
        }
    }
}
